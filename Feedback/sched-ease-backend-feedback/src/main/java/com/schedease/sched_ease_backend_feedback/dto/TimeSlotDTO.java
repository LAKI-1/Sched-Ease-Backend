package com.schedease.sched_ease_backend_feedback.dto;


import lombok.Data;
import java.time.LocalDateTime;

@Data
public class TimeSlotDTO {
    private Long lecturerId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}

