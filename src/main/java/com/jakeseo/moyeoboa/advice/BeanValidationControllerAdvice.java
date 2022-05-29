package com.jakeseo.moyeoboa.advice;

import com.jakeseo.moyeoboa.dto.ErrorResponseDto;
import com.jakeseo.moyeoboa.dto.ValidationErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BeanValidationControllerAdvice {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponseDto handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception
    ) {
        return new ValidationErrorResponseDto(HttpStatus.BAD_REQUEST, MethodArgumentNotValidException.class.getName(), "입력 데이터 검증 오류가 발생하였습니다.", exception.getFieldErrors());
    }
}
