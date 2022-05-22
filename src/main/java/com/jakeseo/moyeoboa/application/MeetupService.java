package com.jakeseo.moyeoboa.application;

import com.jakeseo.moyeoboa.dto.MeetupCreationDto;
import com.jakeseo.moyeoboa.entity.Meetup;
import com.jakeseo.moyeoboa.repository.MeetupJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MeetupService {
    private final MeetupJpaRepository meetupJpaRepository;

    public Meetup create(MeetupCreationDto dto) {
        return meetupJpaRepository.save(new Meetup(dto));
    }
}
