package com.jakeseo.moyeoboa.entity;

import com.jakeseo.moyeoboa.dto.MeetupCreationDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Meetup {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int capacity;
    // TODO: 시, 도, 군과 같은 표준 주소로 구체화하여 리팩토링 예정
    private String place;
    // TODO: User 객체를 따로 구성한 뒤에 User[] 등으로 타입 리팩토링 예정
    @ElementCollection
    @CollectionTable(name = "member")
    private List<String> members;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Meetup(MeetupCreationDto dto) {
        this.name = dto.getName();
        this.capacity = dto.getCapacity();
        this.place = dto.getPlace();
        this.members = dto.getMembers();
        this.startTime = dto.getStartTime();
        this.endTime = dto.getEndTime();
    }

    @Builder
    public Meetup(String name, int capacity, String place, List<String> members, LocalDateTime startTime, LocalDateTime endTime) {
        this.name = name;
        this.capacity = capacity;
        this.place = place;
        this.members = members;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
