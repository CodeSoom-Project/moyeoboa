package com.jakeseo.moyeoboa.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jakeseo.moyeoboa.application.StudyGroupService;
import com.jakeseo.moyeoboa.dto.StudyGroupCreationDto;
import com.jakeseo.moyeoboa.dto.StudyGroupResponseDto;
import com.jakeseo.moyeoboa.entity.StudyGroup;
import com.jakeseo.moyeoboa.repository.StudyGroupJpaRepository;
import com.jakeseo.moyeoboa.annotation.Utf8MockMvc;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Utf8MockMvc
@SpringBootTest
@DisplayName("/study-groups")
public class StudyGroupControllerApiTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    StudyGroupService studyGroupService;
    @Autowired
    StudyGroupJpaRepository studyGroupJpaRepository;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    ModelMapper modelMapper;

    @Nested
    @DisplayName("/ 경로는")
    class Describe_root_path {
        String rootPath = "/study-groups";

        @Nested
        @DisplayName("POST 요청을 받았을 때")
        class Context_post_request {
            @Nested
            @DisplayName("유효한 StudyGroup 정보 JSON 을 받는다면")
            class Context_valid_study_group_data {
                private final MockHttpServletRequestBuilder requestBuilder;
                private final ResultActions resultActions;

                public Context_valid_study_group_data() throws Exception {
                    studyGroupJpaRepository.deleteAll();

                    requestBuilder = post(rootPath);

                    String json = "{\"name\":\"모각코\",\"capacity\":5,\"place\":\"서울\",\"joinUsers\":\"JakeSeo\",\"startTime\":\"2025-05-22 06:32:00\",\"endTime\":\"2025-05-22 11:20:00\"}";

                    requestBuilder
                            .content(json)
                            .contentType(MediaType.APPLICATION_JSON);

                    resultActions = mockMvc.perform(requestBuilder);
                }

                @Test
                @DisplayName("201 CREATED 코드로 응답한다")
                public void it_responses_201_created() throws Exception {
                    resultActions.andExpect(status().isCreated());
                }

                @Test
                @DisplayName("StudyGroupResponseDto 를 반환한다.")
                void it_returns_studyGroupResponseDto() throws Exception {
                    resultActions.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
                    resultActions.andExpect(content().string(containsString("\"name\":\"모각코\",\"capacity\":5,\"place\":\"서울\",\"joinUsers\":\"JakeSeo\",\"startTime\":\"2025-05-22 06:32:00\",\"endTime\":\"2025-05-22 11:20:00\"")));
                    resultActions.andDo(print());
                }

                @Test
                @DisplayName("StudyGroup 을 리포지토리에 저장한다.")
                void it_saves_studyGroup() {
                    assertThat(studyGroupJpaRepository.count()).isNotZero();
                }
            }

            @Nested
            @DisplayName("유효하지 않은 StudyGroup 정보 JSON 을 받는다면")
            class Context_not_valid_studyGroupData {
                private final MockHttpServletRequestBuilder requestBuilder;
                private final ResultActions resultActions;

                public Context_not_valid_studyGroupData() throws Exception {
                    studyGroupJpaRepository.deleteAll();

                    requestBuilder = post(rootPath);

                    String json = "{\"name\":\"\",\"capacity\":5,\"place\":\"서울\",\"joinUsers\":\"JakeSeo\",\"startTime\":\"2025-05-22 06:32:00\",\"endTime\":\"2025-05-22 11:20:00\"}";

                    requestBuilder
                            .content(json)
                            .contentType(MediaType.APPLICATION_JSON);

                    resultActions = mockMvc.perform(requestBuilder);
                }

                @Test
                @DisplayName("400 BAD REQUEST 코드로 응답한다.")
                void it_responses_400_bad_request() throws Exception {
                    resultActions.andExpect(status().isBadRequest());
                }
            }
        }

        @Nested
        @DisplayName("GET 요청을 받았을 때")
        class Context_get_request {
            @Nested
            @DisplayName("저장된 StudyGroup 데이터가 없다면")
            class Context_without_studyGroup_data {
                private final MockHttpServletRequestBuilder requestBuilder;
                private final ResultActions resultActions;

                public Context_without_studyGroup_data() throws Exception {
                    studyGroupJpaRepository.deleteAll();

                    requestBuilder = get(rootPath);
                    resultActions = mockMvc.perform(requestBuilder);
                }

                @Test
                @DisplayName("빈 JSON 리스트를 반환한다.")
                void it_returns_empty_json_list() throws Exception {
                    resultActions.andExpect(content().json("[]"));
                }
                
                @Test
                @DisplayName("200 OK 코드로 응답한다.")
                void it_responses_200_ok() throws Exception {
                    resultActions.andExpect(status().isOk());
                }
            }

            @Nested
            @DisplayName("저장된 StudyGroup 데이터가 있다면")
            class Context_with_studyGroup_data {
                private final MockHttpServletRequestBuilder requestBuilder;
                private final ResultActions resultActions;

                public Context_with_studyGroup_data() throws Exception {
                    studyGroupJpaRepository.deleteAll();

                    StudyGroupCreationDto dto = StudyGroupCreationDto.builder()
                            .name("모각코")
                            .place("서울")
                            .capacity(5)
                            .startTime(LocalDateTime.now())
                            .endTime(LocalDateTime.now().plusHours(5))
                            .joinUsers("Jake Seo")
                            .build();

                    studyGroupService.create(dto);

                    requestBuilder = get(rootPath);
                    resultActions = mockMvc.perform(requestBuilder);
                }

                @Test
                @DisplayName("저장된 StudyGroup 을 리스트 형태로 반환한다.")
                void it_returns_empty_json_list() throws Exception {
                    List<StudyGroupResponseDto> responses = studyGroupService.list().stream()
                            .map((studyGroup -> modelMapper.map(studyGroup, StudyGroupResponseDto.class)))
                            .collect(Collectors.toList());

                    resultActions.andExpect(
                            content().json(objectMapper.writeValueAsString(responses))
                    );

                    resultActions.andDo(print());
                }

                @Test
                @DisplayName("200 OK 코드로 응답한다.")
                void it_responses_200_ok() throws Exception {
                    resultActions.andExpect(status().isOk());
                }
            }
        }
    }

    @Nested
    @DisplayName("/{id} 경로는")
    class Describe_root_path_with_path_variable {
        @Nested
        @DisplayName("GET 요청을 받았을 때")
        class Context_get_request {

            @Nested
            @DisplayName("id 에 해당하는 StudyGroup 데이터가 있다면")
            class Context_with_studyGroup_data {
                private final MockHttpServletRequestBuilder requestBuilder;
                private final ResultActions resultActions;
                private final String pathWithId;
                private final StudyGroup foundStudyGroup;

                public Context_with_studyGroup_data() throws Exception {
                    studyGroupJpaRepository.deleteAll();

                    StudyGroupCreationDto dto = StudyGroupCreationDto.builder()
                            .name("모각코")
                            .place("서울")
                            .capacity(5)
                            .startTime(LocalDateTime.now())
                            .endTime(LocalDateTime.now().plusHours(5))
                            .joinUsers("Jake Seo")
                            .build();

                    foundStudyGroup = studyGroupService.create(dto);
                    pathWithId = "/study-groups/" + foundStudyGroup.getId();

                    requestBuilder = get(pathWithId);
                    resultActions = mockMvc.perform(requestBuilder);
                }

                @Test
                @DisplayName("찾은 StudyGroup 객체를 JSON 형태로 반환한다.")
                void it_returns_json_object() throws Exception {
                    resultActions.andExpect(content().json(objectMapper.writeValueAsString(foundStudyGroup)));
                }

                @Test
                @DisplayName("200 OK 코드로 응답한다.")
                void it_responses_200_ok() throws Exception {
                    resultActions.andExpect(status().isOk());
                }
            }

            @Nested
            @DisplayName("id 에 해당하는 StudyGroup 데이터가 없다면")
            class Context_without_studyGroup_data {
                private final MockHttpServletRequestBuilder requestBuilder;
                private final ResultActions resultActions;
                private final String pathWithId;

                public Context_without_studyGroup_data() throws Exception {
                    studyGroupJpaRepository.deleteAll();

                    pathWithId = "/study-groups/1";

                    requestBuilder = get(pathWithId);
                    resultActions = mockMvc.perform(requestBuilder);
                }

                @Test
                @DisplayName("StudyGroupNotFoundException 예외를 반환한다.")
                void it_throws_not_found_exception() throws Exception {
                    resultActions.andExpect(content().string(containsString("StudyGroupNotFoundException")))
                            .andDo(print());
                }

                @Test
                @DisplayName("404 NOT FOUND 코드로 응답한다.")
                void it_responses_404_not_found() throws Exception {
                    resultActions.andExpect(status().isNotFound());
                }
            }
        }
    }
}