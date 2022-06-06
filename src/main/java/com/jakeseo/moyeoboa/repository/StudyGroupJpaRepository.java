package com.jakeseo.moyeoboa.repository;

import com.jakeseo.moyeoboa.entity.StudyGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudyGroupJpaRepository extends StudyGroupRepository, CrudRepository<StudyGroup, Long> {
    @Override
    List<StudyGroup> findAll();
}
