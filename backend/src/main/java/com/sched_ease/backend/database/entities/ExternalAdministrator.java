package com.sched_ease.backend.database.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "External_Administrator")
public class ExternalAdministrator {

    @Column(name = "External_Administrator_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Id Long id;

    @Column(name = "External_Administrator_Name")
    private String name;

    public ExternalAdministrator() {}
}
