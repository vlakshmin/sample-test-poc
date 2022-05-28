package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.io.File;
import java.io.IOException;

public class ObjectMapperUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T fromJson(Object response, Class<T> dto) {

        return objectMapper.convertValue(response, dto);
    }

    @SneakyThrows
    public static String toJson(Object object) {

        return objectMapper.writeValueAsString(object);
    }

    public static void writeResponseToFile(Object response, String fileRelatedPath) {
        try {
            objectMapper.writeValue(
                    new File(String.format("%s%s", System.getProperty("user.dir"), fileRelatedPath)), response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
