package com.sched_ease.backend.database.entities;


import com.google.gson.JsonObject;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SDGP_Lecturer")
//@PrimaryKeyJoinColumn(name = "SDGP_Lecturer_Id") // Uses the same ID as Lecturer
public class SDGPLecturer extends Lecturer{

    @Column(name = "Supervisor_Flag")
    private boolean supervisorFlag;

    @Column(name = "SDGP_Administrator_Flag")
    private boolean SDGPAdminFlag;

    @Column(name = "Feedback_Instructor_Flag")
    private boolean feedbackInstructorFlag;

    @Column(name = "Viva_Instructor_Flag")
    private boolean vivaInstructorFlag;

    //This is the list viva instructors that help this instructors
    @ManyToMany @JoinTable( name = "Viva-Assist_Instructors", joinColumns = @JoinColumn(name = "Assistant_Viva_Instructors"),  inverseJoinColumns = @JoinColumn(name = "Assisting_Viva_Instructors"))
    private List<SDGPLecturer> assistantVivaInstructors = new ArrayList<>();

    //this is the list of viva instructors that this instructor is supporting
    @ManyToMany(mappedBy = "assistantVivaInstructors")
    private List<SDGPLecturer> assistingVivaInstructors = new ArrayList<>();

    @ManyToMany(mappedBy = "vivaInstructors")
    private List<ConcurrentViva> concurrentVivas = new ArrayList<>();

    @OneToMany(mappedBy = "feedbackInstructorLecturer", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<FeedbackSession> feedbackSessions = new ArrayList<>();

    @OneToMany(mappedBy = "supervisingLecturer", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<SDGPGroup> supervisingSDGPGroups = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "SDGP_Lecturers_Chat_Id", nullable = true)
    private SDGPLecturersChat sDGPLecturersChat;

    @OneToMany(mappedBy = "supervisingLecturer", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<LeadersSupervisorChat> leadersSupervisorChats = new ArrayList<>();

    public SDGPLecturer(){
        super();
    }

    public SDGPLecturer(Lecturer lecturer){
        super(lecturer.getId(), lecturer.getName(), lecturer.getNameShort(), lecturer.getEmail(), lecturer.getTimeTableEntries(), lecturer.getAvailabilities());
    }

    public boolean isSDGPAdminFlag() {
        return SDGPAdminFlag;
    }

    @Override
    public String toString() {
        return "SDGPLecturer{" +
                super.toString() +
                "supervisorFlag=" + supervisorFlag +
                ", SDGPAdminFlag=" + SDGPAdminFlag +
                ", feedbackInstructorFlag=" + feedbackInstructorFlag +
                ", vivaInstructorFlag=" + vivaInstructorFlag +
                ", assistantVivaInstructors=" + assistantVivaInstructors +
                ", assistingVivaInstructors=" + assistingVivaInstructors +
                ", concurrentVivas=" + concurrentVivas +
                ", feedbackSessions=" + feedbackSessions +
                ", supervisingSDGPGroups=" + supervisingSDGPGroups +
                ", sDGPLecturersChat=" + sDGPLecturersChat +
                ", leadersSupervisorChats=" + leadersSupervisorChats +
                '}';
    }

    @Override
    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        json.add("lecturer", super.toJson());  // Convert superclass properties to JSON
//        json.addProperty("leaderFlag", leaderFlag);
        json.addProperty("Supervisor_Flag", supervisorFlag);
        json.addProperty("SDGP_Administrator_Flag", SDGPAdminFlag);
        json.addProperty("Feedback_Instructor_Flag", feedbackInstructorFlag);
        json.addProperty("Viva_Instructor_Flag", vivaInstructorFlag);
        return json;
    }
}
