package com.sched_ease.backend.dto;

import com.sched_ease.backend.database.entities.SDGPGroup;
import java.util.List;
import java.util.stream.Collectors;

public class TeamRegistrationResponse {
    private Long id;
    private String course;
    private int groupNo;
    private boolean registrationStatus;
    private List<StudentDTO> members;

    public static TeamRegistrationResponse fromEntity(SDGPGroup group) {
        TeamRegistrationResponse response = new TeamRegistrationResponse();
        response.setId(group.getId());
        response.setCourse(group.getCourse());
        response.setGroupNo(group.getGroupNo());
        response.setRegistrationStatus(group.isRegistrationStatus());

        // Convert SDGPStudents to StudentDTOs
        if (group.getStudents() != null) {
            response.setMembers(
                    group.getStudents().stream()
                            .map(student -> new StudentDTO(
                                    student.getId(),
                                    student.getName(),
                                    student.getEmail(),
                                    student.getCourse()
                            ))
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

    public boolean isRegistrationStatus() {
        return registrationStatus;
    }

    public void setRegistrationStatus(boolean registrationStatus) {
        this.registrationStatus = registrationStatus;
    }

    public List<StudentDTO> getMembers() {
        return members;
    }

    public void setMembers(List<StudentDTO> members) {
        this.members = members;
    }
}