package com.example.dbcon.dbcon.entities.MinioDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetObjectResponse {
    private String objectName;
    private byte[] content;
}

