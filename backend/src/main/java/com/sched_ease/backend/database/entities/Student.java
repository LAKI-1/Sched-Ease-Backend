package com.sched_ease.backend.database.entities;


import jakarta.persistence.*;

@Entity
@Table(name="Student")
public class Student {

    @Column(name = "Student_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Id Long id;

    @Column(name = "Student_Name")
    private String name;

    @Column(name = "Student_Course")
    private String course;

    @Column(name = "Student_Email")
    private String email;

    @Column(name = "Student_Registration_Semester")
    private String semester;

    @Column(name = "Student_Registration_Year")
    private String year;


    public Student() {}
}
