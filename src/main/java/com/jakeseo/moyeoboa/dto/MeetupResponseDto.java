package com.jakeseo.moyeoboa.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class MeetupResponseDto {
    private Long id;
    private String name;
    private int capacity;
    private String place;
    private List<String> members;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
