package com.sched_ease.backend.database.repositories;

import com.sched_ease.backend.database.entities.VivaSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VivaSessionRepository extends JpaRepository<VivaSession, Long> {
}
