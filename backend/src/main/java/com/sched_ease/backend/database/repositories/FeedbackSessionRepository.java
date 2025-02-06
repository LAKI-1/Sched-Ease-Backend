package com.sched_ease.backend.database.repositories;

import com.sched_ease.backend.database.entities.FeedbackSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackSessionRepository extends JpaRepository<FeedbackSession, Long> {
}
