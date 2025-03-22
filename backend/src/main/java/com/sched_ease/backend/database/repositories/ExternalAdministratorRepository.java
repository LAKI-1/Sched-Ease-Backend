package com.sched_ease.backend.database.repositories;

import com.sched_ease.backend.database.entities.ExternalAdministrator;
import com.sched_ease.backend.database.entities.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExternalAdministratorRepository extends JpaRepository<ExternalAdministrator, Long> {

    Optional<ExternalAdministrator> findByEmail(String email);
}
