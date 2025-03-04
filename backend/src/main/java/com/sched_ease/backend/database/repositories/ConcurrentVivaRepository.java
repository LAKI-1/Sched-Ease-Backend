package com.sched_ease.backend.database.repositories;

import com.sched_ease.backend.database.entities.Chat;
import com.sched_ease.backend.database.entities.ConcurrentViva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcurrentVivaRepository extends JpaRepository<ConcurrentViva, Long> {
}
