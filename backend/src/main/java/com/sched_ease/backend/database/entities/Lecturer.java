package com.sched_ease.backend.database.entities;

import com.google.gson.JsonObject;
import jakarta.persistence.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) // Creates separate tables for each subclass
@Table(name = "Lecturer")
public class Lecturer {

    @Column(name = "Lecturer_Id")
    private @Id Long id;

    @Column(name = "Lecturer_Name")
    private String name;

    @Column(name = "Lecturer_Name_Short")
    private String nameShort;

    @Column(name = "Lecturer_Email")
    private String email;


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

    public Lecturer(Long id, String name, String nameShort, String email, Set<TimeTableEntries> timeTableEntries, List<LecturerAvailability> availabilities) {
        this.id = id;
        this.name = name;
        this.nameShort = nameShort;
        this.email = email;
        this.timeTableEntries = timeTableEntries;
        this.availabilities = (availabilities != null) ? availabilities : new ArrayList<>(); ;
    }

    public Lecturer(Long id, String name){
        this.id = id;
        this.name = name;
    }

    public Lecturer(String name, String nameShort, String email) {
        this.name = name;
        this.nameShort = nameShort;
        this.email = email;
    }

    public Long getId() {
        return id;
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
        return (List<LecturerAvailability>) availabilities;
    }

    public void setAvailabilities(List<LecturerAvailability> availabilities) {
        this.availabilities = availabilities;
    }

    @Override
    public String toString() {
        return "Lecturer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nameShort='" + nameShort + '\'' +
                ", email='" + email + '\'' +
                ", timeTableEntries=" + timeTableEntries +
                ", availabilities=" + availabilities +
                '}';
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        json.addProperty("id", id);
        json.addProperty("name", name);
        json.addProperty("email", email);
        json.addProperty("name-short", nameShort);
//        json.addProperty("tutorial_group", tutorialGroup.getId());
        return json;
    }
}
