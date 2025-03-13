package com.sched_ease.backend.database.repositories;

import com.sched_ease.backend.database.entities.Lecturer;
import com.sched_ease.backend.database.entities.SDGPGroup;
import com.sched_ease.backend.database.entities.SDGPLecturer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SDGPLecturerRepository extends JpaRepository<SDGPLecturer, Long> {

    SDGPLecturer findByEmail(String email);
}
