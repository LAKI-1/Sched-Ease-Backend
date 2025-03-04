package com.sched_ease.backend.database.repositories;

import com.sched_ease.backend.database.entities.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LecturerRepository extends JpaRepository<Lecturer, Long> {
}
