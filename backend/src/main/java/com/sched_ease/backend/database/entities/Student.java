package com.sched_ease.backend.database.entities;

import com.google.gson.JsonObject;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "Student")
public class Student {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Student_Id")
    protected Long id;

    @Column(name = "Student_Name", nullable = true)
    private String name;

    @Column(name = "Student_Course", nullable = true)
    private String course;

    @Column(name = "Student_Email", nullable = true)
    private String email;

    @Column(name = "Student_Registration_Semester", nullable = true)
    private String semester;

    @Column(name = "Student_Registration_Year", nullable = true)
    private String year;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "Student_Group_Id", nullable=true)
    private TutorialGroup tutorialGroup;

    public Student(){}

    public Student(Long id) {
        this.id = id;
    }

    public Student(Long id, String name, String course, String email, String semester, String year, TutorialGroup tutorialGroup) {
        this.id = id;
        this.name = name;
        this.course = course;
        this.email = email;
        this.semester = semester;
        this.year = year;
        this.tutorialGroup = tutorialGroup;
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
        this.year = String.valueOf(year);
    }

    @Override
    public String toString() {
        return ("id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", tutorialGroup=" + tutorialGroup +
                ", course='" + course + "'");
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        json.addProperty("id", id);
        json.addProperty("name", name);
        json.addProperty("email", email);
        json.addProperty("course", course);
        json.addProperty("semester", semester);
        json.addProperty("year", year);
//        json.addProperty("tutorial_group", tutorialGroup.getId());
        return json;
    }
}
