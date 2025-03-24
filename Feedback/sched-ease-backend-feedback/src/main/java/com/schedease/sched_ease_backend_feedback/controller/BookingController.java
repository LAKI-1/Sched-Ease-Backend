package com.schedease.sched_ease_backend_feedback.controller;


import com.schedease.sched_ease_backend_feedback.dto.BookingDTO;
import com.schedease.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    // Endpoint to book a session
    @PostMapping("/bookSession")
    public ResponseEntity<String> bookSession(@RequestBody BookingDTO bookingDTO) {
        try {
            boolean isBooked = bookingService.bookSession(bookingDTO.getStudentId(), bookingDTO.getSessionId());
            if (isBooked) {
                return ResponseEntity.ok("Session booked successfully!");
            } else {
                return ResponseEntity.status(400).body("Failed to book session: Conflicts with lecture/tutorial.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Error: " + e.getMessage());
        }
    }

    // Endpoint to check if a session is available
    @GetMapping("/checkAvailability/{studentId}/{sessionId}")
    public ResponseEntity<String> checkAvailability(@PathVariable Long studentId, @PathVariable Long sessionId) {
        try {
            boolean isAvailable = bookingService.isSlotAvailable(studentId, sessionId);
            if (isAvailable) {
                return ResponseEntity.ok("Slot is available.");
            } else {
                return ResponseEntity.status(400).body("This session slot is already booked or conflicts with your schedule.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Error: " + e.getMessage());
        }
    }
}
