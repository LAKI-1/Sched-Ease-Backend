package com.sched_ease.backend.database.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "Chat_Entries")
public class ChatEntries {

    @Column(name = "Entry_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Id Long id;

    @Column(name = "Content")
    private String content;

    @Column(name = "Timestamp")
    private String timestamp;

    @ManyToOne
    @JoinColumn(name = "chat_id", nullable = false)
    private Chat chat;

    public ChatEntries() {
    }
}
