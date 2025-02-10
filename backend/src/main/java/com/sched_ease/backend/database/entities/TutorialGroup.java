package com.sched_ease.backend.database.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Student_Group")
public class TutorialGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Tutorial_Group_Id")
    private Long id;

    @Column(name = "Tutorial_Group_No")
    private String groupNo;

    @Column(name = "Semester")
    private String semester;

    @Column(name = "Course")
    private String course;

    public TutorialGroup() {}

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupNo() {
        return groupNo;
    }

    public void setGroupNo(String groupNo) {
        this.groupNo = groupNo;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
