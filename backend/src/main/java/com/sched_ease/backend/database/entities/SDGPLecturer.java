package com.sched_ease.backend.database.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "SDGP_Lecturer")
public class SDGPLecturer {

    @Id
    @OneToOne
    @JoinColumn(name = "SDGP_Lecturer_Id")
    private Lecturer SDGPLecturer;

    @Column(name = "Supervisor_Flag")
    private boolean supervisorFlag;

    @Column(name = "SDGP_Administrator_Flag")
    private boolean SDGPAdminFlag;

    @Column(name = "Feedback_Instructor_Flag")
    private boolean feedbackInstructorFlag;

    @Column(name = "Viva_Instructor_Flag")
    private boolean vivaInstructorFlag;

    @ManyToOne
    @JoinColumn(name = "Assistant_Viva_Instructor", nullable = true)
    private Lecturer assistantVivaInstructor;

    public SDGPLecturer(){}
}
