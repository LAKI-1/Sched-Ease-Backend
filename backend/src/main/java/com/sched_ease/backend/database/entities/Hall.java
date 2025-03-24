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

    public Hall(String name, String building, List<FeedbackSession> feedbackSessions) {
        this.name = name;
        this.building = building;
        this.feedbackSessions = feedbackSessions;
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

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public TimeTableEntries getTimeTableEntry() {
        return timeTableEntry;
    }

    public void setTimeTableEntry(TimeTableEntries timeTableEntry) {
        this.timeTableEntry = timeTableEntry;
    }

    public ConcurrentViva getConcurrentViva() {
        return concurrentViva;
    }

    public void setConcurrentViva(ConcurrentViva concurrentViva) {
        this.concurrentViva = concurrentViva;
    }

    public List<FeedbackSession> getFeedbackSessions() {
        return feedbackSessions;
    }

    public void setFeedbackSessions(List<FeedbackSession> feedbackSessions) {
        this.feedbackSessions = feedbackSessions;
    }
}
