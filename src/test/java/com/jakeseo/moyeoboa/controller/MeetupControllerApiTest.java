package com.jakeseo.moyeoboa.controller;

import com.jakeseo.moyeoboa.application.MeetupService;
import com.jakeseo.moyeoboa.repository.MeetupJpaRepository;
import com.jakeseo.moyeoboa.annotation.Utf8MockMvc;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Utf8MockMvc
@SpringBootTest
@DisplayName("/meetups")
public class MeetupControllerApiTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    MeetupService meetupService;
    @Autowired
    MeetupJpaRepository meetupJpaRepository;

    @Nested
    @DisplayName("/ 경로는")
    class Describe_root_path {
        String rootPath = "/meetups";
        @Nested
        @DisplayName("POST 요청을 받았을 때")
        class Context_post_request {
            @Nested
            @DisplayName("올바른 Meetup 정보 JSON 을 함께 받는다면")
            class Context_valid_meetup_data {
                private final MockHttpServletRequestBuilder requestBuilder;
                private final ResultActions resultActions;

                public Context_valid_meetup_data() throws Exception {
                    meetupJpaRepository.deleteAll();

                    requestBuilder = post(rootPath);

                    String json = "{\"name\":\"모각코\",\"capacity\":5,\"place\":\"서울\",\"members\":[\"Me\"],\"startTime\":\"2022-05-22T06:32:00\",\"endTime\":\"2022-05-22T11:20:00\"}";

                    requestBuilder
                            .content(json)
                            .contentType(MediaType.APPLICATION_JSON);

                    resultActions = mockMvc.perform(requestBuilder);
                }

                @Test
                @DisplayName("201 CREATED 응답을 반환한다.")
                public void it_responses_201_created() throws Exception {
                    resultActions.andExpect(status().isCreated());
                }

                @Test
                @DisplayName("MeetupResponseDto 를 반환한다.")
                void it_returns_meetup_response() throws Exception {
                    resultActions.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
                    resultActions.andExpect(content().string(containsString("\"name\":\"모각코\",\"capacity\":5,\"place\":\"서울\",\"members\":[\"Me\"],\"startTime\":\"2022-05-22T06:32:00\",\"endTime\":\"2022-05-22T11:20:00\"")));
                    resultActions.andDo(print());
                }

                @Test
                @DisplayName("Meetup 을 리포지토리에 저장한다.")
                void it_saves_meetup() {
                    assertThat(meetupJpaRepository.count()).isNotZero();
                }
            }
        }
    }
}