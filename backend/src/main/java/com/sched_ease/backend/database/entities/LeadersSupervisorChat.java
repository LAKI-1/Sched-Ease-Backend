package com.sched_ease.backend.database.entities;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "Leader-Supervisor_Chat_Id") // Uses the same ID as Lecturer

@Table(name = "Leader-Supervisor_Chat")
public class LeadersSupervisorChat extends Chat{

//    @Id
//    @OneToOne
//    @JoinColumn(name = "Leader-Supervisor_Chat_Id", referencedColumnName = "Chat_Id")
//    private Chat leaderSupervisorChat;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "Supervising_Lecturer_Id", nullable = true)
    private SDGPLecturer supervisingLecturer;

    @OneToMany(mappedBy = "leadersSupervisorChat", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<SDGPStudent> sDGPLeaders = new ArrayList<>();

    public LeadersSupervisorChat() {
        super();
    }
}
