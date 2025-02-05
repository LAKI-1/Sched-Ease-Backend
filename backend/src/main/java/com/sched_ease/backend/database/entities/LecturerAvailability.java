package com.sched_ease.backend.database.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "Lecturer_Avaialbility")
public class LecturerAvailability {

    @Column(name = "Availability_Entry_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Id Long id;

    @Column(name = "Day_Of_The_Week")
    private String dayOfWeek;

    @Column(name = "Begining_Time")
    private String beginTime;

    @Column(name = "End_Time")
    private String endTime;

    @ManyToOne
    @JoinColumn(name = "Lecturer_Id", nullable = false)
    private Lecturer lecturer;

    public LecturerAvailability() {
    }
}
