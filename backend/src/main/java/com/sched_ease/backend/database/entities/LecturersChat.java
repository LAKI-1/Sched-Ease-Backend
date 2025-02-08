package com.sched_ease.backend.database.entities;


import jakarta.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "Lecturers_Chat_Id") // Uses the same ID as Lecturer
@Table(name = "Lecturers_Chat")
public class LecturersChat extends Chat{

//    @Id
//    @OneToOne
//    @JoinColumn(name = "Lecturers_Chat_Id")
//    private Chat lecturersChat;

    public LecturersChat() {
        super();
    }
}
