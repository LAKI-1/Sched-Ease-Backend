package com.sched_ease.backend.database.repositories;

import com.sched_ease.backend.database.entities.Chat;
import com.sched_ease.backend.database.entities.ChatEntries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {
}
