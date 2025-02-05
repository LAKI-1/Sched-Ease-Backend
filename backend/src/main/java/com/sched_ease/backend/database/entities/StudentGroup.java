package com.sched_ease.backend.database.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "Student_Group")
public class StudentGroup {

    @Column(name = "Student_Group_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Id Long id;

    @Column(name = "Student_Group_No")
    private String groupNo;

    @Column(name = "Semester")
    private String semester;

    @Column(name = "Course")
    private String course;

    @ManyToOne
    @JoinColumn(name = "TimeTable_Id")
    private TimeTable timeTable;

    public StudentGroup() {}
}
