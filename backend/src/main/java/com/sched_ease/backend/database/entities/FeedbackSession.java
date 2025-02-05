package com.sched_ease.backend.database.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "Feedback_Session")
public class FeedbackSession {

    @Column(name = "Session_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Id Long id;

    @Column(name = "Time_And_Date")
    private String timeAndDate;

    @Column(name = "Confirmation")
    private boolean confirmation;

    @Column(name = "Status")
    private String status;

    @Column(name = "Feedback_Content")
    private String feedback;

    @ManyToOne
    @JoinColumn(name = "Feedback_Instructor_Lecturer_Id")
    private Lecturer feedbackInstructorLecturer;

    @ManyToOne
    @JoinColumn(name = "Hall_Id")
    private Hall hall;

    @ManyToOne
    @JoinColumn(name = "SDGP_Group_No")
    private SDGPGroup sdgpGroup;

    public FeedbackSession() {}
}
