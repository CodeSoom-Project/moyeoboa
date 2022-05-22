package com.jakeseo.moyeoboa.controller;

import com.jakeseo.moyeoboa.application.MeetupService;
import com.jakeseo.moyeoboa.dto.MeetupCreationDto;
import com.jakeseo.moyeoboa.dto.MeetupResponseDto;
import com.jakeseo.moyeoboa.entity.Meetup;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/meetups")
@RequiredArgsConstructor
public class MeetupController {
    private final MeetupService meetupService;
    private final ModelMapper modelMapper;

    @GetMapping("")
    public String list() {
        // TODO: meetup 리스트를 가져온다. 리스트 검색은 추후에 지원할 예정이다.
        return "meetup 리스트";
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public MeetupResponseDto create(@RequestBody MeetupCreationDto dto) {
        // TODO: @Valid 를 통해 데이터 검증 추가하기
        return modelMapper.map(meetupService.create(dto), MeetupResponseDto.class);
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id) {
        // TODO: 해당 id 를 가진 meetup 의 상세 정보를 보여준다.
        return "meetup 상세정보";
    }
}
