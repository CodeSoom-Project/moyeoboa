package com.jakeseo.moyeoboa.exception;

public class StudyGroupNotFoundException extends RuntimeException{
    public StudyGroupNotFoundException(Long id) {
        super(String.format("StudyGroup 을 찾지 못했습니다. id: [%d]", id));
    }
}
