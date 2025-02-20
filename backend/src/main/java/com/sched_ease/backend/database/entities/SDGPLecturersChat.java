package com.sched_ease.backend.database.entities;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "Lecturers_Chat_Id") // Uses the same ID as Lecturer
@Table(name = "Lecturers_Chat")
public class SDGPLecturersChat extends Chat{

//    @Id
//    @OneToOne
//    @JoinColumn(name = "Lecturers_Chat_Id")
//    private Chat lecturersChat;
    @OneToMany(mappedBy = "sDGPLecturersChat", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<SDGPLecturer> lecturers = new ArrayList<>();


    public SDGPLecturersChat() {
        super();
    }
}
