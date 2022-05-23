package com.jakeseo.moyeoboa.advice;

import com.jakeseo.moyeoboa.dto.ErrorResponseDto;
import com.jakeseo.moyeoboa.exception.MeetupNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MeetupControllerAdvice {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(MeetupNotFoundException.class)
    public ErrorResponseDto handleNotFound() {
        return new ErrorResponseDto(HttpStatus.NOT_FOUND, MeetupNotFoundException.class.getName(), "Meetup Not Found");
    }
}
