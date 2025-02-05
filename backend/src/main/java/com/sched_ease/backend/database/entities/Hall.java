package com.sched_ease.backend.database.entities;


import jakarta.persistence.*;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id")
    private TimeTableEntries timeTableEntry;

    public Hall() {
    }
}
