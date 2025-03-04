package com.sched_ease.backend.database.entities;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Hall")
public class Hall {

    @Column(name = "Hall_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Id Long id;

    @Column(name = "Hall_Name")
    private String name;

    @Column(name = "Building_Name")
    private String building;

    @Column(name = "Capacity")
    private int capacity;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "Timetable_Entries")
    private TimeTableEntries timeTableEntry;

    @OneToOne(mappedBy = "hall")
    private ConcurrentViva concurrentViva;

    @OneToMany(mappedBy = "hall", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<FeedbackSession> feedbackSessions = new ArrayList<>();

    public Hall() {
    }
}
