package com.sched_ease.backend.database.repositories;

import com.sched_ease.backend.database.entities.LeaderSupervisorChat;
import com.sched_ease.backend.database.entities.SDGPLecturer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaderSupervisorChatRepository extends JpaRepository<LeaderSupervisorChat, Long> {
}
