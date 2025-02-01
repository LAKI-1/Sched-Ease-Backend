package com.sched_ease.backend.database.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Lecturer")
public class Lecturer {

    @Column(name = "Lecturer_Id")
    private @Id Long id;

    @Column(name = "Lecturer_Name")
    private String name;

    @Column(name = "SDGP_Supervisor_Flag")
    private boolean SDGPSupervisorFlag;

    @Column(name = "SDGP_Administrator_Flag")
    private boolean SDGPAdministratorFlag;

    @Column(name = "SDGP_Feedback_Instructor_Flag")
    private boolean SDGPFeedbackInstructorFlag;

    @Column(name = "Viva_Instructor_Flag")
    private boolean VivaInstructorFlag;

    @Column(name = "Viva_Assistant_Flag")
    private boolean VivaAssistantFlag;

    @Transient
    private int someNumber = 98;


    public Lecturer(){}

    public Lecturer(Long id) {
        this.id = id;
    }

    public Lecturer(Long id, String name){
        this.id = id;
        this.name = name;
    }

}
