package com.jakeseo.moyeoboa.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class MeetupCreationDto {
    private String name;
    private int capacity;
    private String place;
    private String joinUsers;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @Builder
    public MeetupCreationDto(String name, int capacity, String place, String joinUsers, LocalDateTime startTime, LocalDateTime endTime) {
        this.name = name;
        this.capacity = capacity;
        this.place = place;
        this.joinUsers = joinUsers;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
