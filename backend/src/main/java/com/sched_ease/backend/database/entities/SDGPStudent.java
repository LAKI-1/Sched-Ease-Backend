package com.sched_ease.backend.database.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "SDGP_Student")
@PrimaryKeyJoinColumn(name = "SDGP_Student_Id") // Uses the same ID as Lecturer
public class SDGPStudent extends Student{

//    @Id
//    @OneToOne
//    @JoinColumn(name = "SDGP_Student_Id", referencedColumnName = "Student_Id")
//    private Student student;

    @Column(name = "SDGP_Leader_Flag")
    private Boolean leaderFlag;

    @ManyToOne
    @JoinColumn(name = "Student_Group_Id")
    private TutorialGroup tutorialGroup;

    @ManyToOne
    @JoinColumn(name = "Sdgp_Gorup_No", nullable = false)
    private SDGPGroup SDGPGroup;

    @ManyToOne
    @JoinColumn(name = "Leader-Supervisor_Chat_Id", nullable = true)
    private LeaderSupervisorChat leaderSupervisorChat;

    public SDGPStudent() {
        super();
    }
}
