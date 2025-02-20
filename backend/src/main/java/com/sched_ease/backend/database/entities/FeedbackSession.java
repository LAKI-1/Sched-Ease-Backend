package com.sched_ease.backend.database.entities;


import com.sched_ease.backend.database.converters.ZonedDateTimeConverter;
import jakarta.persistence.*;

import java.time.ZonedDateTime;

@Entity
@Table(name = "Feedback_Session")
public class FeedbackSession {

    @Column(name = "Session_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Id Long id;

    @Convert(converter = ZonedDateTimeConverter.class)
    @Column(name = "Time_And_Date")
    private ZonedDateTime timeAndDate;

    @Column(name = "Confirmation")
    private boolean confirmation;

    @Column(name = "Status")
    private String status;

    @Column(name = "Feedback_Content")
    private String feedback;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "Feedback_Instructor_Lecturer_Id", nullable = true)
    private SDGPLecturer feedbackInstructorLecturer;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "Hall_Id", nullable = true)
    private Hall hall;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "SDGP_Group_No", nullable = true)
    private SDGPGroup sDGPGroup;

    public FeedbackSession() {}
}
