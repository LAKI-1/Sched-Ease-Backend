package com.sched_ease.backend.database.repositories;

import com.sched_ease.backend.database.entities.Lecturer;
import com.sched_ease.backend.database.entities.SDGPGroup;
import com.sched_ease.backend.database.entities.SDGPLecturer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SDGPLecturerRepository extends JpaRepository<SDGPLecturer, Long> {

    Optional<SDGPLecturer> findByEmail(String email);
}
