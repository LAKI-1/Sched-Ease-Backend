package com.sched_ease.backend.database.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "SDGP_Student")
public class SDGPStudent {

    @Id
    @OneToOne
    @JoinColumn(name = "SDGP_Student_Id")
    private Student student;

    @Column(name = "SDGP_Leader_Flag")
    private Boolean leaderFlag;

    @ManyToOne
    @JoinColumn(name = "Student_Group_Id", nullable = false)
    private StudentGroup studentGroup;

    @ManyToOne
    @JoinColumn(name = "Sdgp_Gorup_No", nullable = false)
    private SDGPGroup SDGPGroup;

    @ManyToOne
    @JoinColumn(name = "Leader-Supervisor_Chat_Id", nullable = true)
    private LeaderSupervisorChat leaderSupervisorChat;

    public SDGPStudent() {}
}
