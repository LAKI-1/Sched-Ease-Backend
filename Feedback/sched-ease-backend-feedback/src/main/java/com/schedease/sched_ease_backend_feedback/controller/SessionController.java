package com.schedease.sched_ease_backend_feedback.controller;


import com.schedease.sched_ease_backend_feedback.dto.SessionDTO;
import com.schedease.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sessions")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    // Endpoint to get all sessions for a specific student
    @GetMapping("/getStudentSessions/{studentId}")
    public ResponseEntity<List<SessionDTO>> getStudentSessions(@PathVariable Long studentId) {
        try {
            List<SessionDTO> sessions = sessionService.getStudentSessions(studentId);
            return ResponseEntity.ok(sessions);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }

    // Endpoint to get all sessions for a specific lecturer
    @GetMapping("/getLecturerSessions/{lecturerId}")
    public ResponseEntity<List<SessionDTO>> getLecturerSessions(@PathVariable Long lecturerId) {
        try {
            List<SessionDTO> sessions = sessionService.getLecturerSessions(lecturerId);
            return ResponseEntity.ok(sessions);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }
}

