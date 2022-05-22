package com.jakeseo.moyeoboa.dto;

import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class MeetupCreationDto {
    private String name;
    private int capacity;
    private String place;
    private List<String> members;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
