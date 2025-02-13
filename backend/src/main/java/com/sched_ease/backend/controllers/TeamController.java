package com.sched_ease.backend.controllers;

import com.sched_ease.backend.database.entities.Team;
import com.sched_ease.backend.database.entities.Student;
import com.sched_ease.backend.dto.TeamResponse;
import com.sched_ease.backend.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @PostMapping("/register")
    public ResponseEntity<TeamResponse> registerTeam(
            @RequestParam String teamType,
            @RequestParam List<Long> memberIds) {

        if (memberIds.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        Team team = teamService.registerTeam(teamType, memberIds);
        List<TeamResponse.MemberDetails> memberDetails = convertMembersToDTO(team.getTeamMembers());

        return ResponseEntity.ok(new TeamResponse(team.getId(), team.getTeamType(), memberDetails));
    }

    @DeleteMapping("/cancel/{teamId}")
    public ResponseEntity<Void> cancelTeamRegistration(@PathVariable Long teamId) {
        teamService.cancelTeamRegistration(teamId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{teamId}")
    public ResponseEntity<TeamResponse> getTeamById(@PathVariable Long teamId) {
        Team team = teamService.getTeamById(teamId).orElse(null);
        if (team == null) {
            return ResponseEntity.notFound().build();
        }
        List<TeamResponse.MemberDetails> memberDetails = convertMembersToDTO(team.getTeamMembers());

        return ResponseEntity.ok(new TeamResponse(team.getId(), team.getTeamType(), memberDetails));
    }

    private List<TeamResponse.MemberDetails> convertMembersToDTO(List<Student> teamMembers) {
        return teamMembers.stream()
                .map(student -> new TeamResponse.MemberDetails(
                        student.getName(),
                        student.getId(),
                        student.getEmail(),
                        student.getTutorialGroup() != null ? student.getTutorialGroup().getGroupNo() : "N/A"
                ))
                .collect(Collectors.toList());
    }
}
