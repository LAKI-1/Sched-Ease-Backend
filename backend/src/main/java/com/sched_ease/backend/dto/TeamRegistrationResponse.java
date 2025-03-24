package com.sched_ease.backend.dto;

import com.sched_ease.backend.database.entities.SDGPGroup;
import java.util.List;
import java.util.stream.Collectors;

public class TeamRegistrationResponse {
    private Long id;
    private String teamId;
    private String course;
    private int groupNo;
    private String status;
    private List<StudentDTO> members;

    public static TeamRegistrationResponse fromEntity(SDGPGroup group) {
        TeamRegistrationResponse response = new TeamRegistrationResponse();
        response.setId(group.getId());
        response.setTeamId(group.getCourse() + "-" + String.format("%02d", group.getGroupNo()));
        response.setCourse(group.getCourse());
        response.setGroupNo(group.getGroupNo());
        response.setStatus(group.isRegistrationStatus() ? "approved" : "pending");

        if (group.getStudents() != null) {
            response.setMembers(
                    group.getStudents().stream()
                            .map(StudentDTO::fromSDGPStudent)
                            .collect(Collectors.toList())
            );
        }

        return response;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getGroupNo() {
        return groupNo;
    }

    public void setGroupNo(int groupNo) {
        this.groupNo = groupNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<StudentDTO> getMembers() {
        return members;
    }

    public void setMembers(List<StudentDTO> members) {
        this.members = members;
    }
}