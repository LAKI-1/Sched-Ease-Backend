package com.sched_ease.backend.database.repositories;

import com.sched_ease.backend.database.entities.SDGPGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SDGPGroupRepository extends JpaRepository<SDGPGroup, Long> {
    @Query("SELECT MAX(g.groupNo) FROM SDGPGroup g WHERE g.course = :course")
    Integer findMaxGroupNumberByCourse(String course);
}
