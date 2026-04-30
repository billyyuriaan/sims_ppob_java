/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.nutech.simsppob.Services;

import java.io.IOException;
import java.net.URI;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;

/**
 *
 * @author iolux
 */
@Service
public class FileService {
    private final S3Client s3Client;

    @Value("${railway.s3.endpointurl}")
    private String endpoint;

    @Value("${railway.s3.bucketname}")
    private String bucket;
        @Value("${railway.s3.id-key}")
    private String accessKey;

    @Value("${railway.s3.secret-key}")
    private String secretKey;

    @Value("${railway.s3.region}")
    private String region;

    public FileService(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public String upload(MultipartFile file) throws IOException {
        String key = file.getOriginalFilename();
        
        s3Client.putObject(
                PutObjectRequest.builder()
                        .bucket(bucket)
                        .key(file.getOriginalFilename())
                        .contentType(file.getContentType())
                        .acl("public-read")
                        .build(),
                RequestBody.fromBytes(file.getBytes()));

        return generatePresignedUrl(key);
    }

    public String generatePresignedUrl(String key) {
        S3Presigner presigner = S3Presigner.builder()
                .region(software.amazon.awssdk.regions.Region.of(region))
                .endpointOverride(URI.create(endpoint))
                .credentialsProvider(
                        StaticCredentialsProvider.create(
                                AwsBasicCredentials.create(accessKey, secretKey)))
                .build();

        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucket)
                .key(key)
                .build();

        GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                .signatureDuration(Duration.ofDays(7))
                .getObjectRequest(getObjectRequest)
                .build();

        return presigner.presignGetObject(presignRequest)
                .url()
                .toString();
    }


}
