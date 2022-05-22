package com.jakeseo.moyeoboa.application;

import com.jakeseo.moyeoboa.dto.MeetupCreationDto;
import com.jakeseo.moyeoboa.entity.Meetup;
import com.jakeseo.moyeoboa.repository.MeetupJpaRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("MeetupService ")
class MeetupServiceTest {
    @Autowired
    MeetupService meetupService;
    @Autowired
    MeetupJpaRepository meetupRepository;

    @Nested
    @DisplayName("create() 메서드는")
    class Describe_create {
        @Nested
        @DisplayName("유효한 MeetupCreationDto 를 받았을 때")
        class Context_valid_input {
            private final Meetup result;
            private final MeetupCreationDto dto;

            public Context_valid_input() {
                dto = MeetupCreationDto.builder()
                        .name("모각코")
                        .place("서울")
                        .capacity(5)
                        .startTime(LocalDateTime.now())
                        .endTime(LocalDateTime.now().plusHours(5))
                        .members(List.of("Jake Seo"))
                        .build();

                result = meetupService.create(dto);
            }

            @Test
            @DisplayName("Meetup 엔티티를 리포지토리에 저장한다.")
            void it_saves_meetup() {
                Assertions.assertThat(meetupRepository.count()).isEqualTo(1);
            }

            @Test
            @DisplayName("정보가 입력된 Meetup 엔티티를 반환한다.")
            void it_returns_meetup() {
                Assertions.assertThat(result).isNotNull();
                Assertions.assertThat(result).isInstanceOf(Meetup.class);

                Assertions.assertThat(result.getName()).isEqualTo(dto.getName());
                Assertions.assertThat(result.getPlace()).isEqualTo(dto.getPlace());
                Assertions.assertThat(result.getStartTime()).isEqualTo(dto.getStartTime());
                Assertions.assertThat(result.getEndTime()).isEqualTo(dto.getEndTime());
                Assertions.assertThat(result.getMembers().toString()).isEqualTo(dto.getMembers().toString());
            }
        }
    }
}