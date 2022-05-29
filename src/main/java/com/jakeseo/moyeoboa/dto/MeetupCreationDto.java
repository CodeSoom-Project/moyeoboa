package com.jakeseo.moyeoboa.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class MeetupCreationDto {
    @NotBlank(message = "모임의 이름을 입력해주세요.")
    private String name;
    @Range(min = 1, max = 1000, message = "모임의 정원은 최소 1명 이상이어야 합니다.")
    private int capacity;
    @NotBlank(message = "모임 장소를 입력해주세요.")
    private String place;
    @NotBlank(message = "최소 1명 이상 참석해야 합니다.")
    private String joinUsers;
    @Future(message = "모임 시작 시간은 과거일 수 없습니다.")
    private LocalDateTime startTime;
    @Future(message = "모임 종료 시간은 과거일 수 없습니다.")
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
