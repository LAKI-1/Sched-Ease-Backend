package com.sched_ease.backend.database.entities;


import jakarta.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "Leader-Supervisor_Chat_Id") // Uses the same ID as Lecturer

@Table(name = "Leader-Supervisor_Chat")
public class LeaderSupervisorChat extends Chat{

//    @Id
//    @OneToOne
//    @JoinColumn(name = "Leader-Supervisor_Chat_Id", referencedColumnName = "Chat_Id")
//    private Chat leaderSupervisorChat;

    @ManyToOne
    @JoinColumn(name = "Supervising_Lecturer_Id", nullable = false)
    private Lecturer supervisingLecturer;

    public LeaderSupervisorChat() {
        super();
    }
}
