package com.jakeseo.moyeoboa.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ErrorResponseDto {
    private final HttpStatus status;
    private final int statusCode;
    private final String exception;
    private final String message;

    public ErrorResponseDto(HttpStatus status, String exception, String message) {
        this.status = status;
        this.statusCode = status.value();
        this.exception = exception;
        this.message = message;
    }
}
