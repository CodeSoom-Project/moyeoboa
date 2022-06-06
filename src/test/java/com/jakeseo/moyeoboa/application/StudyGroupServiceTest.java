package com.jakeseo.moyeoboa.application;

import com.jakeseo.moyeoboa.dto.StudyGroupCreationDto;
import com.jakeseo.moyeoboa.entity.StudyGroup;
import com.jakeseo.moyeoboa.repository.StudyGroupJpaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DisplayName("StudyGroupService ")
class StudyGroupServiceTest {
    @Autowired
    StudyGroupService studyGroupService;
    @Autowired
    StudyGroupJpaRepository studyGroupRepository;

    @Nested
    @DisplayName("create() 메서드는")
    class Describe_create {
        @Nested
        @DisplayName("유효한 StudyGroupCreationDto 를 받았을 때")
        class Context_valid_input {
            private final StudyGroup result;
            private final StudyGroupCreationDto dto;

            public Context_valid_input() {
                studyGroupRepository.deleteAll();

                dto = StudyGroupCreationDto.builder()
                        .name("모각코")
                        .place("서울")
                        .capacity(5)
                        .startTime(LocalDateTime.now())
                        .endTime(LocalDateTime.now().plusHours(5))
                        .joinUsers("JakeSeo")
                        .build();

                result = studyGroupService.create(dto);
            }

            @Test
            @DisplayName("StudyGroup 엔티티를 리포지토리에 저장한다.")
            void it_saves_studyGroup() {
                assertThat(studyGroupRepository.count()).isEqualTo(1);
            }

            @Test
            @DisplayName("정보가 입력된 StudyGroup 엔티티를 반환한다.")
            void it_returns_studyGroup() {
                assertThat(result).isNotNull();
                assertThat(result).isInstanceOf(StudyGroup.class);

                assertThat(result.getName()).isEqualTo(dto.getName());
                assertThat(result.getPlace()).isEqualTo(dto.getPlace());
                assertThat(result.getStartTime()).isEqualTo(dto.getStartTime());
                assertThat(result.getEndTime()).isEqualTo(dto.getEndTime());
                assertThat(result.getJoinUsers()).isEqualTo(dto.getJoinUsers());
            }
        }
    }

    @Nested
    @DisplayName("list() 메서드는")
    class Describe_list {
        @Nested
        @DisplayName("리포지토리에 데이터가 있을 때")
        class Context_with_data_in_repository {
            public Context_with_data_in_repository() {
                studyGroupRepository.deleteAll();

                StudyGroupCreationDto dto = StudyGroupCreationDto.builder()
                        .name("모각코")
                        .place("서울")
                        .capacity(5)
                        .startTime(LocalDateTime.now())
                        .endTime(LocalDateTime.now().plusHours(5))
                        .joinUsers("JakeSeo")
                        .build();

                studyGroupService.create(dto);
            }

            @Test
            @DisplayName("리포지토리에 있는 데이터를 리스트 형태로 반환한다.")
            void it_returns_data_as_list_form() {
                List<StudyGroup> list = studyGroupService.list();
                assertThat(list.size()).isNotZero();
                assertThat(list.get(0).getName()).isEqualTo("모각코");
            }
        }
    }
}