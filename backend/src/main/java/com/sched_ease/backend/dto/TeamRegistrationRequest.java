package com.sched_ease.backend.dto;

import java.util.List;

public class TeamRegistrationRequest {
    private List<Long> studentIds;

    public TeamRegistrationRequest() {
    }

    public TeamRegistrationRequest(List<Long> studentIds) {
        this.studentIds = studentIds;
    }

    public List<Long> getStudentIds() {
        return studentIds;
    }

    public void setStudentIds(List<Long> studentIds) {
        this.studentIds = studentIds;
    }
}