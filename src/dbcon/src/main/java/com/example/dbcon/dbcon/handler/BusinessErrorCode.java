package com.example.dbcon.dbcon.handler;

import org.apache.http.HttpStatus;

import lombok.Getter;

public enum BusinessErrorCode {

    // NO_CODE(0, HttpStatus.SC_NOT_IMPLEMENTED, "No code"),
    // ACCOUNT_LOCED(302, HttpStatus.SC_FORBIDDEN, "Account lcokde"),
    // INCORRECT_CURRENT_PASSWORD(300, HttpStatus.SC_BAD_REQUEST, "Password is incorrect")
    ;

    @Getter
    private final int code;
    @Getter
    private final String description;
    @Getter
    private final HttpStatus httpStatus;

    BusinessErrorCode(int code, HttpStatus httpStatus, String description){
        this.code = code;
        this.httpStatus = httpStatus;
        this.description = description;
    }
}
