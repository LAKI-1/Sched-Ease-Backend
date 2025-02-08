package com.sched_ease.backend.database.repositories;

import com.sched_ease.backend.database.entities.SDGPGroup;
import com.sched_ease.backend.database.entities.SDGPLecturers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SDGPLecturersRepository extends JpaRepository<SDGPLecturers, Long> {
}
