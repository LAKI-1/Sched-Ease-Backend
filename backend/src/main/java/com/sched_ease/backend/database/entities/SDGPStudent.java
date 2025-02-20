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

//    @ManyToOne
//    @JoinColumn(name = "Student_Group_Id", nullable = false)
//    private TutorialGroup studentGroup;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "Sdgp_Gorup_No", nullable = true)
    private SDGPGroup SDGPGroup;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "Leaders-Supervisor_Chat_Id", nullable = true)
    private LeadersSupervisorChat leadersSupervisorChat;

    public SDGPStudent() {
        super();
    }

    public Boolean getLeaderFlag() {
        return leaderFlag;
    }

    public void setLeaderFlag(Boolean leaderFlag) {
        this.leaderFlag = leaderFlag;
    }

    public SDGPGroup getSDGPGroup() {
        return SDGPGroup;
    }

    public void setSDGPGroup(SDGPGroup SDGPGroup) {
        this.SDGPGroup = SDGPGroup;
    }

    public LeadersSupervisorChat getLeaderSupervisorChat() {
        return leadersSupervisorChat;
    }

    public void setLeaderSupervisorChat(LeadersSupervisorChat leaderSupervisorChat) {
        this.leadersSupervisorChat = leaderSupervisorChat;
    }
}
