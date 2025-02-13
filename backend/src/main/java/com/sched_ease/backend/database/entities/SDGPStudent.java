package com.sched_ease.backend.database.entities;


import jakarta.persistence.*;

@Entity
// @PrimaryKeyJoinColumn(name = "SDGP_Student_Id") // Uses the same ID as Lecturer
public class SDGPStudent extends Student{

//    @Id
//    @OneToOne
//    @JoinColumn(name = "SDGP_Student_Id", referencedColumnName = "Student_Id")
//    private Student student;

    @Column(name = "SDGP_Leader_Flag")
    private Boolean leaderFlag;

    @ManyToOne
    @JoinColumn(name = "Student_Group_Id", nullable = false)
    private TutorialGroup studentGroup;

    @ManyToOne
    @JoinColumn(name = "Sdgp_Gorup_No", nullable = false)
    private SDGPGroup SDGPGroup;

    @ManyToOne
    @JoinColumn(name = "Leader-Supervisor_Chat_Id", nullable = true)
    private LeaderSupervisorChat leaderSupervisorChat;

    public SDGPStudent() {
        super();
    }

    public Boolean getLeaderFlag() {
        return leaderFlag;
    }

    public void setLeaderFlag(Boolean leaderFlag) {
        this.leaderFlag = leaderFlag;
    }

    public TutorialGroup getStudentGroup() {
        return studentGroup;
    }

    public void setStudentGroup(TutorialGroup studentGroup) {
        this.studentGroup = studentGroup;
    }

    public SDGPGroup getSDGPGroup() {
        return SDGPGroup;
    }

    public void setSDGPGroup(SDGPGroup SDGPGroup) {
        this.SDGPGroup = SDGPGroup;
    }

    public LeaderSupervisorChat getLeaderSupervisorChat() {
        return leaderSupervisorChat;
    }

    public void setLeaderSupervisorChat(LeaderSupervisorChat leaderSupervisorChat) {
        this.leaderSupervisorChat = leaderSupervisorChat;
    }
}
