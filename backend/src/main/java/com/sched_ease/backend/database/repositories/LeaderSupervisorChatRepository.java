package com.sched_ease.backend.database.repositories;

import com.sched_ease.backend.database.entities.LeadersSupervisorChat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaderSupervisorChatRepository extends JpaRepository<LeadersSupervisorChat, Long> {
}
