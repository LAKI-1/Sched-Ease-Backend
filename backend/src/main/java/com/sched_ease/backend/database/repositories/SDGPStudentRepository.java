package com.sched_ease.backend.database.repositories;

import com.sched_ease.backend.database.entities.SDGPStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SDGPStudentRepository extends JpaRepository<SDGPStudent, Long> {

    Optional<SDGPStudent> findByEmail(String email);
}
