package com.sched_ease.backend.database.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "Viva")
public class Viva {

    @Column(name = "Viva_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Id Long id;

    @Column(name = "Module")
    private String module;

    @Column(name = "Assessment")
    private String assessment;

    @Column(name = "Duration")
    private int duration;

    public Viva() {
    }
}
