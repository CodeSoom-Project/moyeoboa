package com.jakeseo.moyeoboa.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class MeetupResponseDto {
    private Long id;
    private String name;
    private int capacity;
    private String place;
    private String joinUsers;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
