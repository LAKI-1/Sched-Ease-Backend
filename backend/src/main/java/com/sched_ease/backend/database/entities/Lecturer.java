package com.sched_ease.backend.database.entities;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) // Creates separate tables for each subclass
@Table(name = "Lecturer")
public class Lecturer {

    @Column(name = "Lecturer_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Id Long id;

    @Column(name = "Lecturer_Name")
    private String name;

    @Column(name = "Lecturer_Email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "Lecturers_Chat_Id", nullable = false)
    private Chat lecturersChatId;

    @Transient
    private int someNumber = 98;


    public Lecturer(){}

    public Lecturer(Long id) {
        this.id = id;
    }

    public Lecturer(Long id, String name){
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return this.id;
    }
}
