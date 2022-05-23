package com.jakeseo.moyeoboa.application;

import com.jakeseo.moyeoboa.dto.MeetupCreationDto;
import com.jakeseo.moyeoboa.entity.Meetup;
import com.jakeseo.moyeoboa.exception.MeetupNotFoundException;
import com.jakeseo.moyeoboa.repository.MeetupJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MeetupService {
    private final MeetupJpaRepository meetupJpaRepository;

    public Meetup create(MeetupCreationDto dto) {
        return meetupJpaRepository.save(new Meetup(dto));
    }

    public List<Meetup> list() {
        return meetupJpaRepository.findAll();
    }

    public Meetup detail(Long id) {
        return meetupJpaRepository.findById(id)
                .orElseThrow(() -> new MeetupNotFoundException(id));
    }
}
