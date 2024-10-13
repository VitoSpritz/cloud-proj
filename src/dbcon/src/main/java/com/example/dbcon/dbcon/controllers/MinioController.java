package com.example.dbcon.dbcon.controllers;

import io.jsonwebtoken.io.IOException;
import io.minio.*;
import io.minio.errors.MinioException;
import io.minio.messages.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.dbcon.dbcon.entities.MinioDTO.PutObjectRequest;
import com.example.dbcon.dbcon.entities.MinioDTO.GetObjectResponse;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("api/minio")
public class MinioController {

    @Autowired
    private MinioClient minioClient;

    @Value("${minio.bucket-name}")
    private String bucketName;

    @GetMapping("/images/user/{userId}")
    public ResponseEntity<byte[]> getImageByUserId(@PathVariable String userId) throws InvalidKeyException, NoSuchAlgorithmException, IllegalArgumentException, java.io.IOException {
        try {
            // Supponiamo che il nome del file sia {userId}.jpeg
            String imageName = userId + ".jpeg";
            
            // Ottenere l'immagine dal bucket
            InputStream stream = minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(bucketName)
                            .object(imageName)
                            .build()
            );
            
            // Lettura dell'immagine in byte array
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            byte[] data = new byte[1024];
            int bytesRead;
            while ((bytesRead = stream.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, bytesRead);
            }
            byte[] imageBytes = buffer.toByteArray();

            // Impostare l'header del tipo di contenuto
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "image/jpeg");

            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } catch (MinioException | IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*
    @GetMapping("/listObjects")
    public ResponseEntity<List<String>> listObjects(@RequestParam(required = false) String prefix) {
        try {
            List<String> objectNames = new ArrayList<>();
            ListObjectsArgs args = ListObjectsArgs.builder().bucket(bucketName).prefix(prefix).build();
            Iterable<Result<Item>> results = minioClient.listObjects(args);
            for (Result<Item> result : results) {
                objectNames.add(result.get().objectName());
            }
            return ResponseEntity.ok(objectNames);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @GetMapping("/getObject")
    public ResponseEntity<GetObjectResponse> getObject(@RequestParam String objectName) {


        try (InputStream stream = minioClient.getObject(GetObjectArgs.builder()
                .bucket(bucketName).object(objectName).build())) {

            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            byte[] data = new byte[1024];
            int bytesRead = 0;
            while ((bytesRead = stream.read(data)) != -1) {
                buffer.write(data, 0, bytesRead);
            }

            GetObjectResponse response = new GetObjectResponse();
            response.setObjectName(objectName);
            response.setContent(buffer.toByteArray());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @PostMapping("/putObject")
    public ResponseEntity<String> putObject(@RequestBody PutObjectRequest request) {

        
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(request.getContent());
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(request.getObjectName())
                    .stream(inputStream, request.getContent().length, -1)
                    .contentType(request.getContentType())
                    .build());
            return ResponseEntity.ok("Object uploaded successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error uploading object: " + e.getMessage());
        }
    }

    @DeleteMapping("/removeObject")
    public ResponseEntity<String> removeObject(@RequestParam String objectName) {
        try {
            minioClient.removeObject(RemoveObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .build());
            return ResponseEntity.ok("Object removed successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error removing object: " + e.getMessage());
        }
    }*/
}
