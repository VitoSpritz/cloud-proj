package com.example.dbcon.dbcon.controllers;

import io.jsonwebtoken.io.IOException;
import io.minio.*;
import io.minio.errors.MinioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("api/minio")
public class MinioController {

    @Autowired
    private MinioClient minioClient;

    @Value("${minio.bucket-name}")
    private String bucketName;

    @Value("${minio.url}")
    private String minioUrl;

    @GetMapping("/images/user/{userId}")
    public ResponseEntity<byte[]> getImageByUserId(@PathVariable String userId) throws InvalidKeyException, NoSuchAlgorithmException, IllegalArgumentException, java.io.IOException {
        try {
            String imageName = userId + ".jpeg";
            
            InputStream stream = minioClient.getObject(
                    GetObjectArgs.builder()
                        .bucket(bucketName)
                        .object(imageName)
                        .build()
            );
            
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            byte[] data = new byte[1024];
            int bytesRead;
            while ((bytesRead = stream.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, bytesRead);
            }
            byte[] imageBytes = buffer.toByteArray();

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "image/jpeg");

            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } catch (MinioException | IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/images/upload/{userId}")
    @PreAuthorize("hasAnyAuthority('GROUP_/Admins', 'GROUP_/IT', 'GROUP_/Users')")
    public ResponseEntity<String> uploadImageWithUserId(@PathVariable String userId, @RequestParam("file") MultipartFile file) throws IllegalArgumentException, java.io.IOException {
        try {
            String objectName = userId + ".jpeg";
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .contentType("image/jpeg")
                            .build()
            );

            return ResponseEntity.ok("Image uploaded successfully for user ID: " + userId);
        } catch (IOException | InvalidKeyException | NoSuchAlgorithmException | MinioException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image");
        }
    }

}