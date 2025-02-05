package com.sched_ease.backend.database.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "TimeTable_Entries")
public class TimeTableEntries {

    @Column(name = "Entry_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Id Long id;

    @Column(name = "Level")
    private String level;

    @Column(name = "Course")
    private String course;

    @Column(name = "Day_Of_Week")
    private String dayOfWeek;

    @Column(name = "Start_Time")
    private String startTime;

    @Column(name = "End_Time")
    private String endTime;

    @Column(name = "Lecture/Tutorial")
    private String lectureOrTutorial;

    @ManyToOne
    @JoinColumn(name = "TimeTable_Id", nullable = false)
    private TimeTable timeTable;

    public TimeTableEntries() {
    }

    public Long getId() {
        return this.id;
    }
}
