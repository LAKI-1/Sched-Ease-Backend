package com.sched_ease.backend.database.repositories;

import com.sched_ease.backend.database.entities.LogEntry;
import com.sched_ease.backend.database.entities.SDGPLecturers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogEntryRepository extends JpaRepository<LogEntry, Long> {
}
