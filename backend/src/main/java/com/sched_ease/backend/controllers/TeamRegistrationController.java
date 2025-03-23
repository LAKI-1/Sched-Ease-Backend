package com.sched_ease.backend.controllers;

import com.sched_ease.backend.database.entities.SDGPGroup;
import com.sched_ease.backend.dto.TeamApprovalRequest;
import com.sched_ease.backend.dto.TeamRegistrationRequest;
import com.sched_ease.backend.dto.TeamRegistrationResponse;
import com.sched_ease.backend.exception.ErrorDetails;
import com.sched_ease.backend.services.TeamRegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/teams")
@CrossOrigin(origins = "http://localhost:3000")
public class TeamRegistrationController {
    private static final Logger logger = LoggerFactory.getLogger(TeamRegistrationController.class);

    @Autowired
    private TeamRegistrationService teamRegistrationService;

    @GetMapping
    public ResponseEntity<List<TeamRegistrationResponse>> getAllTeams() {
        try {
            logger.info("Fetching all teams");
            List<TeamRegistrationResponse> teams = teamRegistrationService.getAllTeams().stream()
                    .map(TeamRegistrationResponse::fromEntity)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(teams);
        } catch (Exception e) {
            logger.error("Failed to fetch teams", e);
            return ResponseEntity.badRequest()
                    .body(null);
        }
    }

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

    @PostMapping("/{teamId}/approve")
    public ResponseEntity<?> approveTeam(@PathVariable Long teamId, @RequestBody TeamApprovalRequest request) {
        try {
            logger.info("Received team approval request for team {} with leader {}", teamId, request.getLeaderId());
            SDGPGroup approvedGroup = teamRegistrationService.approveTeam(teamId, request.getLeaderId());
            TeamRegistrationResponse response = TeamRegistrationResponse.fromEntity(approvedGroup);
            logger.info("Successfully approved team {}", teamId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Failed to approve team", e);
            return ResponseEntity.badRequest()
                    .body(new ErrorDetails("Failed to approve team: " + e.getMessage()));
        }
    }

    @PostMapping("/{teamId}/reject")
    public ResponseEntity<?> rejectTeam(@PathVariable Long teamId) {
        try {
            logger.info("Received team rejection request for team {}", teamId);
            SDGPGroup rejectedGroup = teamRegistrationService.rejectTeam(teamId);
            TeamRegistrationResponse response = TeamRegistrationResponse.fromEntity(rejectedGroup);
            logger.info("Successfully rejected team {}", teamId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Failed to reject team", e);
            return ResponseEntity.badRequest()
                    .body(new ErrorDetails("Failed to reject team: " + e.getMessage()));
        }
    }
}