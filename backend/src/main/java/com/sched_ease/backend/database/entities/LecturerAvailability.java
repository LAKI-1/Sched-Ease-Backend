package com.sched_ease.backend.database.entities;


import com.sched_ease.backend.database.converters.ZonedDateTimeConverter;
import jakarta.persistence.*;

import java.time.DayOfWeek;
import java.time.ZonedDateTime;

@Entity
@Table(name = "Lecturer_Avaialbility")
public class LecturerAvailability {

    @Column(name = "Availability_Entry_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Id Long id;

    @Column(name = "Day_Of_The_Week")
    private String dayOfWeek;

    @Convert(converter = ZonedDateTimeConverter.class)
    @Column(name = "Begining_Time")
    private ZonedDateTime beginTime;

    @Convert(converter = ZonedDateTimeConverter.class)
    @Column(name = "End_Time")
    private ZonedDateTime endTime;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "Lecturer_Id", nullable = true)
    private Lecturer lecturer;

    public LecturerAvailability() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public ZonedDateTime getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(ZonedDateTime beginTime) {
        this.beginTime = beginTime;
    }

    public ZonedDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(ZonedDateTime endTime) {
        this.endTime = endTime;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }
}
