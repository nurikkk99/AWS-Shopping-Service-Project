package com.epam.adminservice.config;

import java.net.URI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;

@Configuration("s3")
public class S3Config {
    @Bean
    S3Client s3Client(@Value("${s3.endpointURI}") String uri, @Value("${s3.bucketName}") String bucketName) {
        CreateBucketRequest createBucketRequest= CreateBucketRequest.builder().bucket(bucketName).build();
        S3Client s3Client = S3Client.builder().endpointOverride(URI.create(uri))
                .region(Region.US_EAST_1)
                .build();
        s3Client.createBucket(createBucketRequest);
        return s3Client;
    }
}
