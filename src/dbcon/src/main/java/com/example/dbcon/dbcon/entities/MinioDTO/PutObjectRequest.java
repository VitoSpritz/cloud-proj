package com.example.dbcon.dbcon.entities.MinioDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PutObjectRequest {

    private String objectName;
    private String contentType;
    private byte[] content;
}
