package api.services.gcp;

import com.google.api.services.storage.StorageScopes;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;

import static io.qameta.allure.Allure.step;
import static java.lang.String.format;

@Slf4j
public class GoogleCloudStorageService {

    private Blob blob;
    private Storage storage;
    private String objectName;
    private String destFilePath;
    private String projectId = "tr-rnd";
    private String bucketName = "tr-rnd";
    private String folderName = "tr-reg-ui";
    private GoogleCredentials credentials;

    private static final String TEMP_FOLDER = System.getProperty("java.io.tmpdir");

    public GoogleCloudStorageService() throws IOException {
        credentials = setCredentials();
        storage = createStorage(credentials);
    }

    private Storage createStorage(GoogleCredentials credentials) {
        log.info("Creating new Client using GoogleCloud Credentials object");

        return StorageOptions.newBuilder()
                .setProjectId(projectId)
                .setCredentials(credentials)
                .build()
                .getService();
    }

    private GoogleCredentials setCredentials() throws IOException {
        log.info("Setting up GoogleCloud Credentials using 'client_secrets.json' from resources");

        return GoogleCredentials.fromStream(
                Objects.requireNonNull(ClassLoader.getSystemResourceAsStream("client_secrets.json")))
                .createScoped(Arrays.asList(
                        StorageScopes.CLOUD_PLATFORM,
                        StorageScopes.DEVSTORAGE_READ_ONLY));
    }

    public void downloadObjectToTempFolder(String fileName) {
        log.info("Downloading file '{}' from bucket '{}' and inner folder '{}' to '{}'",
                fileName, bucketName, folderName, TEMP_FOLDER);
        step(format("Downloading file '%s' from bucket '%s' and inner folder '%s' to '%s'",
                fileName, bucketName, folderName, TEMP_FOLDER));

        // The ID of your GCS object
        objectName = format("%s/%s", folderName, fileName);
        destFilePath = format("%s/%s", TEMP_FOLDER, fileName);

        blob = storage.get(BlobId.of(bucketName, objectName));
        blob.downloadTo(Paths.get(destFilePath));

        log.info("Downloaded object '{}' from bucket name '{}' to '{}'", objectName, bucketName, destFilePath);
    }
}

