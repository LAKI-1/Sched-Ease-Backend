package com.sched_ease.backend.database.repositories;

import com.sched_ease.backend.database.entities.TimeTableEntries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeTableEntriesRepository extends JpaRepository<TimeTableEntries, Long> {
}
