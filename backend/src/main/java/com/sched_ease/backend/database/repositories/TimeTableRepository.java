package com.sched_ease.backend.database.repositories;

import com.sched_ease.backend.database.entities.TimeTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeTableRepository extends JpaRepository<TimeTable, Long> {
}
