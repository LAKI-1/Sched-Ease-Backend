package com.sched_ease.backend.database.repositories;

import com.sched_ease.backend.database.entities.StudentGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentGroupRepository extends JpaRepository<StudentGroup, Long> {
}
