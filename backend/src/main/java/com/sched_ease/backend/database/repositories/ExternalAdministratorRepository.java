package com.sched_ease.backend.database.repositories;

import com.sched_ease.backend.database.entities.ExternalAdministrator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExternalAdministratorRepository extends JpaRepository<ExternalAdministrator, Long> {
}
