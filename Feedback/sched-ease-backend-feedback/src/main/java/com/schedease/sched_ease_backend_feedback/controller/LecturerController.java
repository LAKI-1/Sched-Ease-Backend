package com.schedease.sched_ease_backend_feedback.controller;



import com.schedease.sched_ease_backend_feedback.dto.SessionDTO;
import com.schedease.service.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lecturer")
public class LecturerController {

    @Autowired
    private LecturerService lecturerService;

    // Endpoint to upload available time slots for a lecturer
    @PostMapping("/uploadAvailableSlots")
    public ResponseEntity<String> uploadAvailableSlots(@RequestBody SessionDTO sessionDTO) {
        try {
            lecturerService.uploadAvailableSlots(sessionDTO);
            return ResponseEntity.ok("Available slots uploaded successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Error: " + e.getMessage());
        }
    }

    // Endpoint to get all available time slots for a specific lecturer
    @GetMapping("/getAvailableSlots/{lecturerId}")
    public ResponseEntity<List<String>> getAvailableSlots(@PathVariable Long lecturerId) {
        try {
            List<String> availableSlots = lecturerService.getAvailableSlots(lecturerId);
            return ResponseEntity.ok(availableSlots);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }
}
