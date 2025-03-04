package com.sched_ease.backend.database.entities;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Concurrent_Viva")
public class ConcurrentViva {

    @Column(name = "Concurrent_Viva_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Id Long id;

//    @Column(name = "Module")
//    private String module;
//
//    @Column(name = "Assessment")
//    private String assessment;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "Viva_Id", nullable = true)
    private Viva viva;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "Hall_Id", nullable = true)
    private Hall hall;

    @ManyToMany
    @JoinTable(
            name = "Concurrent_Viva_Lecturer",
            joinColumns = @JoinColumn(name = "Concurrent_Viva_Id"),
            inverseJoinColumns = @JoinColumn(name = "Lecturer_Id")
    )
    private Set<SDGPLecturer> vivaInstructors = new HashSet<>();

    @OneToMany(mappedBy = "concurrentViva", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<VivaSession> concurrentVivas = new ArrayList<>();

    public ConcurrentViva() {}

    public Long getId() {
        return this.id;
    }
}
