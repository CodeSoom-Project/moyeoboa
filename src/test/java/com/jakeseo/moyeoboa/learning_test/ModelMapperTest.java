package com.jakeseo.moyeoboa.learning_test;

import com.jakeseo.moyeoboa.dto.MeetupResponseDto;
import com.jakeseo.moyeoboa.entity.Meetup;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
@DisplayName("ModelMapper 라이브러리")
public class ModelMapperTest {

    @Autowired
    ModelMapper modelMapper;

    @Nested
    @DisplayName("map() 메서드는")
    class Describe_map {
        @Nested
        @DisplayName("meetup 인스턴스와 MeetupResponseDto 클래스 정보를 받았을 때")
        class Context_meetup_instance_and_meetup_response_dto_class {
            @Test
            @DisplayName("meetup 인스턴스가 매핑된 MeetupResponseDto 클래스를 반환한다.")
            void it_returns_mapped_meetup_response_dto() {
                Meetup meetup = Meetup.builder()
                        .name("모각코")
                        .place("서울")
                        .capacity(5)
                        .startTime(LocalDateTime.now())
                        .endTime(LocalDateTime.now().plusHours(5))
                        .joinUsers("Jake Seo")
                        .build();

                MeetupResponseDto responseDto = modelMapper.map(meetup, MeetupResponseDto.class);

                Assertions.assertThat(responseDto).isNotNull();
                Assertions.assertThat(responseDto).isInstanceOf(MeetupResponseDto.class);

                Assertions.assertThat(responseDto.getName()).isEqualTo(meetup.getName());
                Assertions.assertThat(responseDto.getPlace()).isEqualTo(meetup.getPlace());
                Assertions.assertThat(responseDto.getStartTime()).isEqualTo(meetup.getStartTime());
                Assertions.assertThat(responseDto.getEndTime()).isEqualTo(meetup.getEndTime());
                Assertions.assertThat(responseDto.getJoinUsers()).isEqualTo(meetup.getJoinUsers());
            }
        }
    }
}
