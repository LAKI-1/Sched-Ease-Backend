package com.sched_ease.backend.dto;

import java.util.List;

public class TeamResponse {

    private Long teamId;
    private String teamType;
    private List<MemberDetails> teamMembers;

    public static class MemberDetails {
        private String name;
        private Long studentId;
        private String email;
        private String tutorialGroup;

        public MemberDetails(String name, Long studentId, String email, String tutorialGroup) {
            this.name = name;
            this.studentId = studentId;
            this.email = email;
            this.tutorialGroup = tutorialGroup;
        }

        // Getters and setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Long getStudentId() {
            return studentId;
        }

        public void setStudentId(Long studentId) {
            this.studentId = studentId;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getTutorialGroup() {
            return tutorialGroup;
        }

        public void setTutorialGroup(String tutorialGroup) {
            this.tutorialGroup = tutorialGroup;
        }
    }

    public TeamResponse(Long teamId, String teamType, List<MemberDetails> teamMembers) {
        this.teamId = teamId;
        this.teamType = teamType;
        this.teamMembers = teamMembers;
    }

    // Getters and setters
    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getTeamType() {
        return teamType;
    }

    public void setTeamType(String teamType) {
        this.teamType = teamType;
    }

    public List<MemberDetails> getTeamMembers() {
        return teamMembers;
    }

    public void setTeamMembers(List<MemberDetails> teamMembers) {
        this.teamMembers = teamMembers;
    }
}
