package zutils;

import com.codeborne.selenide.Configuration;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.awaitility.core.ConditionTimeoutException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

public final class FileUtils {

    public static File getFileByName(String dir, String fileName) throws IOException {

        Path path = Paths.get(dir);

        if (!Files.isDirectory(path)) {
            throw new IllegalArgumentException("Path must be a directory!");
        }

        File file;

        try (Stream<Path> walk = Files.walk(path)) {
            file = new File(walk
                    .filter(Files::isReadable)
                    .filter(Files::isRegularFile)
                    .filter(p -> p.getFileName().toString().equalsIgnoreCase(fileName))
                    .findFirst()
                    .get().toString());
        }

        return file;
    }

    public static void waitFileDownloading(String fileName) throws IOException {

        Path filePath = Paths.get(Configuration.downloadsFolder);

        if (!Files.isDirectory(filePath)) {
            throw new IllegalArgumentException("Path must be a directory!");
        }

        try (Stream<Path> walk = Files.walk(filePath)) {
            await().atMost(30, SECONDS)
                    .ignoreExceptions()
                    .until(() -> (new File(walk
                            .filter(Files::isReadable)
                            .filter(Files::isRegularFile)
                            .filter(p -> p.getFileName().toString().equalsIgnoreCase(fileName))
                            .findFirst()
                            .get().toFile().toString()).exists()));
        } catch (ConditionTimeoutException e) {
            System.out.println(String.format("File %s not found in the folder %s", fileName, filePath));
        }


    }

    private static List<Path> getFilesByName(String dir, String fileName) throws IOException {
        Path path = Paths.get(dir);
        if (!Files.isDirectory(path)) {
            throw new IllegalArgumentException("Path must be a directory!");
        }

        List<Path> result;

        try (Stream<Path> walk = Files.walk(path)) {
            result = walk
                    .filter(Files::isReadable)
                    .filter(Files::isRegularFile)
                    .filter(p -> p.getFileName().toString().equalsIgnoreCase(fileName))
                    .collect(Collectors.toList());
        }

        return result;
    }

    public static void deleteFileByName(String fileName) throws IOException {

        List<Path> result = getFilesByName(Configuration.downloadsFolder, fileName);

        for (Path filepath : result) {
            if (!Files.deleteIfExists(filepath)) {
                System.out.println(String.format("File %s is not deleted ", filepath));
            }
        }

    }

    public static List<String[]> getAllDataFromCSVWithoutHeader(String dir, String filename) throws IOException {

        List<String[]> data = new ArrayList<>();

        File file = getFileByName(dir, filename);

        try {
            FileReader filereader = new FileReader(file.getPath());
            CSVReader csvReader = new CSVReaderBuilder(filereader)
                    .withSkipLines(1)
                    .build();
            data = csvReader.readAll();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public static String[] getHeader(String dir, String filename) throws IOException {

        File file = getFileByName(dir, filename);

        String[] header = {};

        try {
            FileReader filereader = new FileReader(file.getPath());
            CSVReader csvReader = new CSVReaderBuilder(filereader)
                    .build();
            List<String[]> allData = csvReader.readAll();
            header = allData.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return header;
    }
}
