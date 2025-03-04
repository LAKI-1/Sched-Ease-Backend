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
}
