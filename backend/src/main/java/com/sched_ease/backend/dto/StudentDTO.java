package com.sched_ease.backend.dto;

import com.sched_ease.backend.database.entities.SDGPStudent;
import com.sched_ease.backend.database.entities.Student;

public class StudentDTO {
    private Long id;
    private String name;
    private String email;
    private String course;
    private String tutorialGroup;
    private boolean isLeader;

    public StudentDTO() {
    }

    public StudentDTO(Long id, String name, String email, String course, String tutorialGroup, boolean isLeader) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.course = course;
        this.tutorialGroup = tutorialGroup;
        this.isLeader = isLeader;
    }

    // Static factory method to create DTO from Student entity
    public static StudentDTO fromEntity(Student student) {
        return new StudentDTO(
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getCourse(),
                student.getTutorialGroup() != null ? student.getTutorialGroup().getGroupNo() : null,
                student instanceof SDGPStudent ? ((SDGPStudent) student).getLeaderFlag() : false
        );
    }

    // Static factory method specifically for SDGPStudent
    public static StudentDTO fromSDGPStudent(SDGPStudent student) {
        return new StudentDTO(
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getCourse(),
                student.getTutorialGroup() != null ? student.getTutorialGroup().getGroupNo() : null,
                student.getLeaderFlag()
        );
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getTutorialGroup() {
        return tutorialGroup;
    }

    public void setTutorialGroup(String tutorialGroup) {
        this.tutorialGroup = tutorialGroup;
    }

    public boolean isLeader() {
        return isLeader;
    }

    public void setLeader(boolean leader) {
        isLeader = leader;
    }
}