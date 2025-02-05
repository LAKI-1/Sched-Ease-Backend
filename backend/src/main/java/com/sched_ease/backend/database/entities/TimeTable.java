package com.sched_ease.backend.database.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "TimeTable")
public class TimeTable {

    @Column(name = "TimeTable_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Id Long id;

    @Column(name = "Date_Of_Creation")
    private String dateOfCreation;

    public TimeTable() {
    }
}
