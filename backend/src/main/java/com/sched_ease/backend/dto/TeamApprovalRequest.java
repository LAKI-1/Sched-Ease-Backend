package com.sched_ease.backend.dto;

public class TeamApprovalRequest {
    private Long LeaderId;

    public TeamApprovalRequest() {}

    public TeamApprovalRequest(Long leaderId) {
        LeaderId = leaderId;
    }

    public Long getLeaderId() {
        return LeaderId;
    }

    public void setLeaderId(Long leaderId) {
        LeaderId = leaderId;
    }
}
