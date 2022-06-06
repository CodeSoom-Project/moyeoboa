package com.jakeseo.moyeoboa.application;

import com.jakeseo.moyeoboa.dto.StudyGroupCreationDto;
import com.jakeseo.moyeoboa.entity.StudyGroup;
import com.jakeseo.moyeoboa.exception.StudyGroupNotFoundException;
import com.jakeseo.moyeoboa.repository.StudyGroupJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudyGroupService {
    private final StudyGroupJpaRepository studyGroupJpaRepository;

    public StudyGroup create(StudyGroupCreationDto dto) {
        return studyGroupJpaRepository.save(new StudyGroup(dto));
    }

    public List<StudyGroup> list() {
        return studyGroupJpaRepository.findAll();
    }

    public StudyGroup detail(Long id) {
        return studyGroupJpaRepository.findById(id)
                .orElseThrow(() -> new StudyGroupNotFoundException(id));
    }
}
