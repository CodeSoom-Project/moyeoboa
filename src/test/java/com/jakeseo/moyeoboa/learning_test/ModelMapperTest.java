package com.jakeseo.moyeoboa.learning_test;

import com.jakeseo.moyeoboa.dto.StudyGroupResponseDto;
import com.jakeseo.moyeoboa.entity.StudyGroup;
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
        @DisplayName("studyGroup 인스턴스와 StudyGroupResponseDto 클래스 정보를 받았을 때")
        class Context_studyGroup_instance_and_studyGroupResponseDto_class {
            @Test
            @DisplayName("studyGroup 인스턴스가 매핑된 StudyGroupResponseDto 클래스를 반환한다.")
            void it_returns_mapped_study_group_response_dto() {
                StudyGroup studyGroup = StudyGroup.builder()
                        .name("모각코")
                        .place("서울")
                        .capacity(5)
                        .startTime(LocalDateTime.now())
                        .endTime(LocalDateTime.now().plusHours(5))
                        .joinUsers("Jake Seo")
                        .build();

                StudyGroupResponseDto responseDto = modelMapper.map(studyGroup, StudyGroupResponseDto.class);

                Assertions.assertThat(responseDto).isNotNull();
                Assertions.assertThat(responseDto).isInstanceOf(StudyGroupResponseDto.class);

                Assertions.assertThat(responseDto.getName()).isEqualTo(studyGroup.getName());
                Assertions.assertThat(responseDto.getPlace()).isEqualTo(studyGroup.getPlace());
                Assertions.assertThat(responseDto.getStartTime()).isEqualTo(studyGroup.getStartTime());
                Assertions.assertThat(responseDto.getEndTime()).isEqualTo(studyGroup.getEndTime());
                Assertions.assertThat(responseDto.getJoinUsers()).isEqualTo(studyGroup.getJoinUsers());
            }
        }
    }
}
