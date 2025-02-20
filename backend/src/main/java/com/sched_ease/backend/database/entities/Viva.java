package com.sched_ease.backend.database.entities;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

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
    private int standardDuration;

    @Column(name = "No_Of_Concurrent_Vivas")
    private int noOfConcurrentVivas;

    @OneToMany(mappedBy = "viva", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<ConcurrentViva> concurrentVivas = new ArrayList<>();

    public Viva() {
    }
}
