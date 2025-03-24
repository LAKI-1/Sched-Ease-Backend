package com.schedease.sched_ease_backend_feedback.dto;


import lombok.Data;

@Data
public class AuthenticationRequest {
    private String username;
    private String password;
}

