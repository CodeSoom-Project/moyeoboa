package com.jakeseo.moyeoboa.repository;

import com.jakeseo.moyeoboa.entity.Meetup;

import java.util.List;

public interface MeetupRepository {
    List<Meetup> findAll();
}
