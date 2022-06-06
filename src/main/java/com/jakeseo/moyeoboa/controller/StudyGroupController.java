package com.jakeseo.moyeoboa.controller;

import com.jakeseo.moyeoboa.application.StudyGroupService;
import com.jakeseo.moyeoboa.dto.StudyGroupCreationDto;
import com.jakeseo.moyeoboa.dto.StudyGroupResponseDto;
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

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/study-groups")
@RequiredArgsConstructor
public class StudyGroupController {
    private final StudyGroupService studyGroupService;
    private final ModelMapper modelMapper;

    @GetMapping("")
    public List<StudyGroupResponseDto> list() {
        return studyGroupService.list().stream()
                .map((studyGroup -> modelMapper.map(studyGroup, StudyGroupResponseDto.class)))
                .collect(Collectors.toList());
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public StudyGroupResponseDto create(@Valid @RequestBody StudyGroupCreationDto dto) {
        return modelMapper.map(studyGroupService.create(dto), StudyGroupResponseDto.class);
    }

    @GetMapping("/{id}")
    public StudyGroupResponseDto detail(@PathVariable Long id) {
        return modelMapper.map(studyGroupService.detail(id), StudyGroupResponseDto.class);
    }
}
