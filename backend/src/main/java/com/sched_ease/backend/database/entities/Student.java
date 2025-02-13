package com.sched_ease.backend.database.entities;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Student_Id")
    private Long id;

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

    @ManyToOne
    @JoinColumn(name = "Tutorial_Group_Id")
    private TutorialGroup tutorialGroup;

    @ManyToOne
    @JoinColumn(name = "Team_Id")  // Foreign key should be here
    private Team team;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TutorialGroup getTutorialGroup() {
        return tutorialGroup;
    }

    public void setTutorialGroup(TutorialGroup tutorialGroup) {
        this.tutorialGroup = tutorialGroup;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
