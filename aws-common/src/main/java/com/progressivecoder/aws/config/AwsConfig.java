package com.progressivecoder.aws.config;

import com.progressivecoder.aws.services.S3Service;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class AwsConfig {

    private static final String AWS_REGION = "us-east-1";
    private static final String AWS_S3_BUCKET_NAME = "s3-test-bkt-1898";

    @Bean
    public S3Client s3Client() {
        return S3Client.builder()
                .region(Region.of(AWS_REGION))
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();
    }

    @Bean
    public String s3BucketName() {
        return AWS_S3_BUCKET_NAME;
    }

    @Bean
    public S3Service s3Service(S3Client s3Client) {
        return new S3Service(s3Client, AWS_S3_BUCKET_NAME);
    }
}
