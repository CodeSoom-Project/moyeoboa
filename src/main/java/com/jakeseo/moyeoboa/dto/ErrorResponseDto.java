package com.jakeseo.moyeoboa.dto;

import lombok.Getter;

@Getter
public class ErrorResponseDto {
    private final String exception;
    private final String message;

    public ErrorResponseDto(String exception, String message) {
        this.exception = exception;
        this.message = message;
    }
}
