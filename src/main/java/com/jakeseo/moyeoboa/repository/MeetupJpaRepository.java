package com.jakeseo.moyeoboa.repository;

import com.jakeseo.moyeoboa.entity.Meetup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeetupJpaRepository extends MeetupRepository, CrudRepository<Meetup, Long> {
    @Override
    List<Meetup> findAll();
}
