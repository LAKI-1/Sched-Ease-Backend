package com.sched_ease.backend.database.entities;

import jakarta.persistence.*;

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

    @OneToOne
    @JoinColumn(name = "Group_Chat_Id")
    private Chat groupChat;

    @ManyToOne
    @JoinColumn(name = "Viva_Session_Id")
    private VivaSession vivaSession;

    @ManyToOne
    @JoinColumn(name = "Supervising_Lecturer_Id")
    private Lecturer supervisingLecturer;

    public SDGPGroup(){}

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

    public Chat getGroupChat() {
        return groupChat;
    }

    public void setGroupChat(Chat groupChat) {
        this.groupChat = groupChat;
    }

    public VivaSession getVivaSession() {
        return vivaSession;
    }

    public void setVivaSession(VivaSession vivaSession) {
        this.vivaSession = vivaSession;
    }

    public Lecturer getSupervisingLecturer() {
        return supervisingLecturer;
    }

    public void setSupervisingLecturer(Lecturer supervisingLecturer) {
        this.supervisingLecturer = supervisingLecturer;
    }
}
