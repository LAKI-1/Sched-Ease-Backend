package com.schedease.sched_ease_backend_feedback.dto;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SessionDTO {
    private Long sessionId;
    private Long lecturerId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean available;
}
