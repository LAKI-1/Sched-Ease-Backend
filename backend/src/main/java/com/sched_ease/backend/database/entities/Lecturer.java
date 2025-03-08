package com.sched_ease.backend.database.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    private List<LecturerAvailability> availabilities = new ArrayList<>();


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

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameShort() {
        return nameShort;
    }

    public void setNameShort(String nameShort) {
        this.nameShort = nameShort;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<TimeTableEntries> getTimeTableEntries() {
        return timeTableEntries;
    }

    public void setTimeTableEntries(Set<TimeTableEntries> timeTableEntries) {
        this.timeTableEntries = timeTableEntries;
    }

    public List<LecturerAvailability> getAvailabilities() {
        return availabilities;
    }

    public void setAvailabilities(List<LecturerAvailability> availabilities) {
        this.availabilities.clear();
        if (availabilities != null) {
            this.availabilities.addAll(availabilities);
        }
    }

    // Method to add a single availability
    public void addAvailability(LecturerAvailability availability) {
        if (availability != null) {
            availability.setLecturer(this);
            this.availabilities.add(availability);
        }
    }

    public void removeAvailability(LecturerAvailability availability) {
        if (availability != null) {
            this.availabilities.remove(availability);
            availability.setLecturer(null);
        }
    }
}
