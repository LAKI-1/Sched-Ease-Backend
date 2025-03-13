package com.sched_ease.backend.database.repositories;

import com.sched_ease.backend.database.entities.ExternalAdministrator;
import com.sched_ease.backend.database.entities.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExternalAdministratorRepository extends JpaRepository<ExternalAdministrator, Long> {

    ExternalAdministrator findByEmail(String email);
}
