package com.sched_ease.backend.database.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "Viva_Session")
public class VivaSession {

    @Column(name = "Viva_Session_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Id Long id;

    @Column(name = "Timestamp")
    private String timestamp;

    @ManyToOne
    @JoinColumn(name = "Concurrent_Viva_Id")
    private ConcurrentViva concurrentViva;

    public VivaSession() {
    }
}
