package com.epam.adminservice.repository;

import java.io.IOException;
import java.net.URI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import org.springframework.beans.factory.annotation.Value;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Repository
public class ImageS3Repository {

    private static Logger logger = LoggerFactory.getLogger(ImageS3Repository.class);
    private final String BUCKET_NAME;
    private String URL;
    private final S3Client s3;

    public ImageS3Repository(@Value("${s3.endpointURI}") String URL,
            @Value("${s3.bucketName}") String bucketName, S3Client s3Client) {
        this.s3 = s3Client;
        this.URL = URL;
        this.BUCKET_NAME = bucketName;
    }

    public String saveImage(String imageKey, MultipartFile file) throws IOException {
        logger.info("Saving image");
        PutObjectRequest putObjectRequest = PutObjectRequest.builder().bucket(BUCKET_NAME).key(imageKey).build();
        RequestBody requestBody = RequestBody.fromBytes(file.getBytes());
        s3.putObject(putObjectRequest, requestBody);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(URL).append("/").append(BUCKET_NAME).append("/").append(imageKey);
        return stringBuilder.toString();
    }
}
