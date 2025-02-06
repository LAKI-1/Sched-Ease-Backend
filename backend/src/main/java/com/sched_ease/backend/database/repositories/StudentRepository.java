package com.sched_ease.backend.database.repositories;

import com.sched_ease.backend.database.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
