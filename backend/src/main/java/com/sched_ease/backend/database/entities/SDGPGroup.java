package com.sched_ease.backend.database.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SDGP_Group")
public class SDGPGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SDGP_Group_Id")
    private Long id;

    @Column(name = "SDGP_Group_No")
    private int groupNo;

    @Column(name = "Course")
    private String course;

    @Column(name = "Registration_Status")
    private boolean registrationStatus = false;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "SDGP_Group_Chat_Id", nullable = true)
    private SDGPGroupChat sDGPGroupChat;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "Viva_Session_Id", nullable = true)
    private VivaSession vivaSession;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "Supervising_Lecturer_Id", nullable = true)
    private SDGPLecturer supervisingLecturer;

    @OneToMany(mappedBy = "sDGPGroup", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<FeedbackSession> feedbackSessions = new ArrayList<>();

    @OneToMany(mappedBy = "sdgpGroup", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<LogEntry> logEntries = new ArrayList<>();

    @OneToMany(mappedBy = "SDGPGroup", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<SDGPStudent> students = new ArrayList<>();

    public SDGPGroup(){}

    public SDGPGroup(int groupNo, String course, boolean registrationStatus, List<SDGPStudent> students) {
        this.groupNo = groupNo;
        this.course = course;
        this.registrationStatus = registrationStatus;
        this.supervisingLecturer = supervisingLecturer;
        this.feedbackSessions = feedbackSessions;
        this.students = students;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getGroupNo() {
        return groupNo;
    }

    public void setGroupNo(int groupNo) {
        this.groupNo = groupNo;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public boolean isRegistrationStatus() {
        return registrationStatus;
    }

    public void setRegistrationStatus(boolean registrationStatus) {
        this.registrationStatus = registrationStatus;
    }

    public SDGPGroupChat getSDGPGroupChat() {
        return sDGPGroupChat;
    }

    public void setSDGPGroupChat(SDGPGroupChat groupChat) {
        this.sDGPGroupChat = groupChat;
    }

    public VivaSession getVivaSession() {
        return vivaSession;
    }

    public void setVivaSession(VivaSession vivaSession) {
        this.vivaSession = vivaSession;
    }

    public SDGPLecturer getSupervisingLecturer() {
        return supervisingLecturer;
    }

    public void setSupervisingLecturer(SDGPLecturer supervisingLecturer) {
        this.supervisingLecturer = supervisingLecturer;
    }

    public List<SDGPStudent> getStudents() {
        return students;
    }

    public void setStudents(List<SDGPStudent> students) {
        this.students = students;
    }

    public SDGPStudent getGroupLeader() {
        for (SDGPStudent leader : students){
            if (leader.getLeaderFlag()){
                return leader;
            }
        }
        return null;
    }

    public List<SDGPStudent> getMembersOnly() {
        List members = new ArrayList();
        for (SDGPStudent member : students){
            if (!member.getLeaderFlag()){
                members.add(member);
            }
        }
        return members;
    }
}
