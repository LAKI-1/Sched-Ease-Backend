package com.sched_ease.backend.controllers;

import com.sched_ease.backend.dto.SessionRequestDTO;
import com.sched_ease.backend.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sessions")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @PostMapping("/add-to-group/{groupId}")
    public ResponseEntity<String> addSessionToGroup(@PathVariable Long groupId,
                                                    @RequestBody List<SessionRequestDTO> sessionRequestList) {
        try {
            for (SessionRequestDTO request : sessionRequestList) {
                sessionService.addSessionToGroup(
                        groupId,
                        request.getLecturerIds(),
                        request.getHallId(),
                        request.getLevel(),
                        request.getCourse(),
                        request.getDayOfWeek(),
                        request.getStartTime(),
                        request.getEndTime(),
                        request.getLectureOrTutorial(),
                        request.getTimeTableId()
                );
            }
            return ResponseEntity.ok("Sessions added successfully!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}