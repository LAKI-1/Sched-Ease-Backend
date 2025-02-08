package com.sched_ease.backend.database.repositories;

import com.sched_ease.backend.database.entities.LeaderSupervisorChat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LecturersChatRepository extends JpaRepository<LeaderSupervisorChat, Long> {
}
