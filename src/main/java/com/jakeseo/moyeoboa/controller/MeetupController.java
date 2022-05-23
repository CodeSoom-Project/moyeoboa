package com.jakeseo.moyeoboa.controller;

import com.jakeseo.moyeoboa.application.MeetupService;
import com.jakeseo.moyeoboa.dto.MeetupCreationDto;
import com.jakeseo.moyeoboa.dto.MeetupResponseDto;
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

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/meetups")
@RequiredArgsConstructor
public class MeetupController {
    private final MeetupService meetupService;
    private final ModelMapper modelMapper;

    @GetMapping("")
    public List<MeetupResponseDto> list() {
        return meetupService.list().stream()
                .map((meetup -> modelMapper.map(meetup, MeetupResponseDto.class)))
                .collect(Collectors.toList());
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public MeetupResponseDto create(@RequestBody MeetupCreationDto dto) {
        // TODO: @Valid 를 통해 데이터 검증 추가하기
        return modelMapper.map(meetupService.create(dto), MeetupResponseDto.class);
    }

    @GetMapping("/{id}")
    public MeetupResponseDto detail(@PathVariable Long id) {
        return modelMapper.map(meetupService.detail(id), MeetupResponseDto.class);
    }
}
