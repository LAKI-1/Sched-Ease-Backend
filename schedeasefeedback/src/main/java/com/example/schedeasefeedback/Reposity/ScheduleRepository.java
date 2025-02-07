package com.example.schedeasefeedback.Reposity;


import com.example.schedeasefeedback.Entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByUserId(Long userId);
}

