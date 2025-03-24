package com.schedease.sched_ease_backend_feedback.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "lecturers")
public class Lecturer extends User {

    @Column(nullable = false)
    private String department;

    @OneToMany(mappedBy = "lecturer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Session> sessions;
}

