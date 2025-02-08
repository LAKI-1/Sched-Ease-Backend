package com.sched_ease.backend.database.repositories;

import com.sched_ease.backend.database.entities.ChatEntries;
import com.sched_ease.backend.database.entities.ConcurrentVivaInstructors;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcurrentVivaInstructorsRepository extends JpaRepository<ConcurrentVivaInstructors, Long> {
}
