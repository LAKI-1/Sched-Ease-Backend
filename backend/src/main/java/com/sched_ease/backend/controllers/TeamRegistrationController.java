package com.sched_ease.backend.controllers;

import com.sched_ease.backend.database.entities.SDGPGroup;
import com.sched_ease.backend.dto.TeamRegistrationRequest;
import com.sched_ease.backend.dto.TeamRegistrationResponse;
import com.sched_ease.backend.exception.ErrorDetails;
import com.sched_ease.backend.services.TeamRegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teams")
@CrossOrigin(origins = "*")
public class TeamRegistrationController {
    private static final Logger logger = LoggerFactory.getLogger(TeamRegistrationController.class);

    @Autowired
    private TeamRegistrationService teamRegistrationService;

    @PostMapping("/register")
    public ResponseEntity<?> registerTeam(@RequestBody TeamRegistrationRequest request) {
        try {
            logger.info("Received team registration request with {} members", request.getStudentIds().size());
            SDGPGroup registeredGroup = teamRegistrationService.registerTeam(request);
            TeamRegistrationResponse response = TeamRegistrationResponse.fromEntity(registeredGroup);
            logger.info("Successfully registered team with group number: {}-{}",
                    registeredGroup.getCourse(), registeredGroup.getGroupNo());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Failed to register team", e);
            return ResponseEntity.badRequest()
                    .body(new ErrorDetails("Failed to register team: " + e.getMessage()));
        }
    }
}