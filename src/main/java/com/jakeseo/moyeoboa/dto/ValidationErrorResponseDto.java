package com.jakeseo.moyeoboa.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
public class ValidationErrorResponseDto extends ErrorResponseDto {
    Set<ValidationError> validationErrors = new HashSet<>();

    public ValidationErrorResponseDto(HttpStatus status, String exception, String message, List<FieldError> fieldErrors) {
        super(status, exception, message);

        for (FieldError fieldError : fieldErrors) {
            this.validationErrors.add(new ValidationError(fieldError.getField(), fieldError.getDefaultMessage()));
        }
    }

    @Getter
    static class ValidationError {
        String field;
        String message;

        public ValidationError(String field, String message) {
            this.field = field;
            this.message = message;
        }
    }
}
