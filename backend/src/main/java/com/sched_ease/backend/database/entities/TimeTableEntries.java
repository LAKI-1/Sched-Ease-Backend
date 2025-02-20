package com.sched_ease.backend.database.entities;


import com.sched_ease.backend.database.converters.ZonedDateTimeConverter;
import jakarta.persistence.*;

import java.time.DayOfWeek;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TimeTable_Entries")
public class TimeTableEntries {

    @Column(name = "Entry_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Id Long id;

    @Column(name = "Level")
    private String level; //will need to somehow convert to int

    @Column(name = "Course")
    private String course;

    @Column(name = "Day_Of_Week")
    private String dayOfWeek;

    @Convert(converter = ZonedDateTimeConverter.class)
    @Column(name = "Start_Time")
    private ZonedDateTime startTime;

    @Convert(converter = ZonedDateTimeConverter.class)
    @Column(name = "End_Time")
    private ZonedDateTime endTime;

    @Column(name = "Lecture/Tutorial")
    private String lectureOrTutorial;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "TimeTable_Id", nullable = true)
    private TimeTable timeTable;

    @OneToOne(mappedBy = "timeTableEntry")
    private Hall hall;

    @ManyToMany(mappedBy = "timeTableEntries")
    private Set<Lecturer> lecturers = new HashSet<>();

    public TimeTableEntries() {
    }

    public Long getId() {
        return this.id;
    }
}
