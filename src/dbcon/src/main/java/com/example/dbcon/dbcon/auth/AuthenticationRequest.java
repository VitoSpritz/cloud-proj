package com.example.dbcon.dbcon.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthenticationRequest {

    @Email(message = "Email is not formatted")
    @NotEmpty(message = "email is mandatory")
    @NotBlank(message = "email is mandatory")
    private String email;
    @NotEmpty(message = "password is mandatory")
    @NotBlank(message = "password is mandatory")
    private String password;
}
