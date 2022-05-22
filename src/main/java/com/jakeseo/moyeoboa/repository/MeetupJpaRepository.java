package com.jakeseo.moyeoboa.repository;

import com.jakeseo.moyeoboa.entity.Meetup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetupJpaRepository extends CrudRepository<Meetup, Long> {

}
