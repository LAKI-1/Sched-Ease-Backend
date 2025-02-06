package com.sched_ease.backend.database.repositories;

import com.sched_ease.backend.database.entities.LecturerAvailability;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LecturerAvailabilityRepository extends JpaRepository<LecturerAvailability, Long> {
}
