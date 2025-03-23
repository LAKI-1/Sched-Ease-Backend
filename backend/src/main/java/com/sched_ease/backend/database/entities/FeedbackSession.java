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
    @Column(name = "Start_Date_Time")
    private ZonedDateTime starDateTime;

    @Convert(converter = ZonedDateTimeConverter.class)
    @Column(name = "End_Date_Time")
    private ZonedDateTime endDateTime;

    @Column(name = "Confirmation")
    private boolean confirmation;

    @Column(name = "Status")
    private String status;

    @Column(name = "Feedback_Content")
    private String feedback;

    @Column(name = "Feedback_Title")
    private String title;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "Feedback_Instructor_Lecturer_Id", nullable = true)
    private SDGPLecturer feedbackInstructorLecturer;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "Hall_Id", nullable = true)
    private Hall hall;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "SDGP_Group_No", nullable = true)
    private SDGPGroup sDGPGroup;

    public FeedbackSession() {}

    public Long getId() {
        return id;
    }

    public ZonedDateTime getStarDateTime() {
        return starDateTime;
    }

    public boolean isConfirmation() {
        return confirmation;
    }

    public ZonedDateTime getEndDateTime() {
        return endDateTime;
    }

    public String getStatus() {
        return status;
    }

    public String getFeedback() {
        return feedback;
    }

    public SDGPLecturer getFeedbackInstructorLecturer() {
        return feedbackInstructorLecturer;
    }

    public Hall getHall() {
        return hall;
    }

    public SDGPGroup getsDGPGroup() {
        return sDGPGroup;
    }

    public String getTitle() {
        return title;
    }
}
