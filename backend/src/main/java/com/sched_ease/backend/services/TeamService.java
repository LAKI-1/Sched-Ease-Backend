package com.sched_ease.backend.services;

import com.sched_ease.backend.database.entities.Student;
import com.sched_ease.backend.database.entities.Team;
import com.sched_ease.backend.database.repositories.TeamRepository;
import com.sched_ease.backend.database.repositories.StudentRepository;
import com.sched_ease.backend.exception.MemberNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private StudentRepository studentRepository;

    public Team registerTeam(String teamType, List<Long> memberIds) {
        List<Student> teamMembers = studentRepository.findAllById(memberIds);
        if (teamMembers.size() != memberIds.size()) {
            throw new MemberNotFoundException("Some members not found");
        }

        // Optional team size validation
        if (teamMembers.size() > 10) { // Example size limit
            throw new IllegalArgumentException("A team cannot have more than 10 members.");
        }

        Team team = new Team();
        team.setTeamType(teamType);
        team.setTeamMembers(teamMembers);

        return teamRepository.save(team);
    }

    public void cancelTeamRegistration(Long teamId) {
        if (!teamRepository.existsById(teamId)) {
            throw new IllegalArgumentException("Team not found for cancellation");
        }
        teamRepository.deleteById(teamId);
    }

    public Optional<Team> getTeamById(Long teamId) {
        return teamRepository.findById(teamId);
    }
}
