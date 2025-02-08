package com.sched_ease.backend.database.repositories;

import com.sched_ease.backend.database.entities.TutorialGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorialGroupRepository extends JpaRepository<TutorialGroup, Long> {
}
