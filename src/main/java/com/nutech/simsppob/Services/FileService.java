/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.nutech.simsppob.Services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

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

    public FileService(S3Client s3Client){
        this.s3Client = s3Client;
    }


    public String upload(MultipartFile file) throws IOException {
        s3Client.putObject(
                PutObjectRequest.builder()
                        .bucket(bucket)
                        .key(file.getOriginalFilename())
                        .contentType(file.getContentType())
                        .build(),
                RequestBody.fromBytes(file.getBytes())
        );

        String url = endpoint + "/" + bucket + "/" + file.getOriginalFilename();

        return url;
    }
}
