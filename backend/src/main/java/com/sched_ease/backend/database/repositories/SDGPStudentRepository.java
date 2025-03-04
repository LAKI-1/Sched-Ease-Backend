package com.sched_ease.backend.database.repositories;

import com.sched_ease.backend.database.entities.SDGPGroup;
import com.sched_ease.backend.database.entities.SDGPStudent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SDGPStudentRepository extends JpaRepository<SDGPStudent, Long> {
}
