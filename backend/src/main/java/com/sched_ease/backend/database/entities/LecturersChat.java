package com.sched_ease.backend.database.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "Lecturers_Chat")
public class LecturersChat {

    @Id
    @OneToOne
    @JoinColumn(name = "Lecturers_Chat_Id")
    private Chat lecturersChat;

    public LecturersChat() {
    }
}
