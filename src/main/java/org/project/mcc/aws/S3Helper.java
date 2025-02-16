package org.project.mcc.aws;

import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
public class S3Helper {

    private final String BUCKET_NAME = "mcc-tesis-bucket-carlos-villarreal";

    protected void saveFileInS3(final String localPathString) throws IOException {
        Path localPath = Path.of(localPathString);
        if (Files.notExists(localPath)) {
            log.warn("Given path {} does not exist, so it cannot be saved in S3", localPathString);
        }

        setupBucket();

        RequestBody body = RequestBody.fromBytes(Files.readAllBytes(localPath));
        putObjectInBucket(localPath.getFileName().toString(), body);
    }

    private S3Client getS3Client() {
        return S3Client
                .builder()
                .region(Region.US_EAST_1)
                .build();
    }

    private void setupBucket() {
        createBucketIfNotExists();
        createFolderIfNotExists();
    }

    private void createBucketIfNotExists() {
        CreateBucketRequest createBucketRequest = CreateBucketRequest
                .builder()
                .bucket(BUCKET_NAME)
                .build();
        try {
            getS3Client().createBucket(createBucketRequest);
        } catch (BucketAlreadyExistsException e) {
           log.warn("Bucket {} already exists", BUCKET_NAME);
        }
    }

    private void createFolderIfNotExists() {
        putObjectInBucket("", RequestBody.empty());
    }

    private void putObjectInBucket(String fileName, RequestBody body) {
        final String jsonFolderName = "input_files/" + fileName;

        PutObjectRequest request = PutObjectRequest
                .builder()
                .bucket(BUCKET_NAME)
                .key(jsonFolderName)
                .build();

        getS3Client().putObject(request, body);
        log.info("Object {} put in bucket {}", jsonFolderName, BUCKET_NAME);
    }


}
