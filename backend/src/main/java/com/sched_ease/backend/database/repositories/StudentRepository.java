package com.sched_ease.backend.database.repositories;

import com.sched_ease.backend.database.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

//    Student findByEmail(String email);
    Optional<Student> findByEmail(String email);
}
