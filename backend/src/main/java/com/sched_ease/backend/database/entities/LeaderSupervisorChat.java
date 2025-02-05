package com.sched_ease.backend.database.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "Leader-Supervisor_Chat")
public class LeaderSupervisorChat {

    @Id
    @OneToOne
    @JoinColumn(name = "Leader-Supervisor_Chat_Id")
    private Chat leaderSupervisorChat;

    @ManyToOne
    @JoinColumn(name = "Supervising_Lecturer_Id", nullable = false)
    private Lecturer supervisingLecturer;

    public LeaderSupervisorChat() {}
}
