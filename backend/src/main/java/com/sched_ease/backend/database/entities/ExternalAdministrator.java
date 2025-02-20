package com.sched_ease.backend.database.entities;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "External_Administrator")
public class ExternalAdministrator {

    @Column(name = "External_Administrator_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Id Long id;

    @Column(name = "External_Administrator_Name")
    private String name;

    @OneToMany(mappedBy = "administrator", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<TimeTable> timeTables = new ArrayList<>();

    public ExternalAdministrator() {}
}
