package com.jakeseo.moyeoboa.advice;

import com.jakeseo.moyeoboa.dto.ErrorResponseDto;
import com.jakeseo.moyeoboa.exception.StudyGroupNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class StudyGroupControllerAdvice {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(StudyGroupNotFoundException.class)
    public ErrorResponseDto handleNotFound() {
        return new ErrorResponseDto(HttpStatus.NOT_FOUND, StudyGroupNotFoundException.class.getName(), "모임을 찾을 수 없습니다.");
    }
}
