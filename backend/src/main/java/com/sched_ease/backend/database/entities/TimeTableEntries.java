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

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "Tutorial_Group_Id", nullable = true)
    private TutorialGroup tutorialGroup;

    public TimeTableEntries() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public ZonedDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(ZonedDateTime startTime) {
        this.startTime = startTime;
    }

    public ZonedDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(ZonedDateTime endTime) {
        this.endTime = endTime;
    }

    public String getLectureOrTutorial() {
        return lectureOrTutorial;
    }

    public void setLectureOrTutorial(String lectureOrTutorial) {
        this.lectureOrTutorial = lectureOrTutorial;
    }

    public TimeTable getTimeTable() {
        return timeTable;
    }

    public void setTimeTable(TimeTable timeTable) {
        this.timeTable = timeTable;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public Set<Lecturer> getLecturers() {
        return lecturers;
    }

    public void setLecturers(Set<Lecturer> lecturers) {
        this.lecturers = lecturers;
    }

    public TutorialGroup getTutorialGroup() {
        return tutorialGroup;
    }

    public void setTutorialGroup(TutorialGroup tutorialGroup) {
        this.tutorialGroup = tutorialGroup;
    }
}
