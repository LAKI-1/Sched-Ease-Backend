package com.sched_ease.backend.database.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) // Creates separate tables for each subclass
@Table(name = "Lecturer")
public class Lecturer {

    @Column(name = "Lecturer_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Id Long id;

    @Column(name = "Lecturer_Name")
    private String name;

    @Column(name = "Lecturer_Name_Short")
    private String nameShort;

    @Column(name = "Lecturer_Email")
    private String email;

//    @ManyToOne
//    @JoinColumn(name = "Lecturers_Chat_Id", nullable = false)
//    private Chat lecturersChatId;

//    @Transient
//    private int someNumber = 98;

    @ManyToMany
    @JoinTable(
            name = "Lecturer-TimeTable_Entries",
            joinColumns = @JoinColumn(name = "Lecturer_Id"),
            inverseJoinColumns = @JoinColumn(name = "TimeTableEntry_Id")
    )
    private Set<TimeTableEntries> timeTableEntries = new HashSet<>();

    @OneToMany(mappedBy = "lecturer", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private ArrayList<LecturerAvailability> availabilities = new ArrayList<>();


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
