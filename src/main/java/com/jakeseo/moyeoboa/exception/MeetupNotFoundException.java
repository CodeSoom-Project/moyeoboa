package com.jakeseo.moyeoboa.exception;

public class MeetupNotFoundException extends RuntimeException{
    public MeetupNotFoundException(Long id) {
        super(String.format("Meetup 을 찾지 못했습니다. id: [%d]", id));
    }
}
