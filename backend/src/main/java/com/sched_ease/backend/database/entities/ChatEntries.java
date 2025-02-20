package com.sched_ease.backend.database.entities;


import com.sched_ease.backend.database.converters.ZonedDateTimeConverter;
import jakarta.persistence.*;

import java.time.ZonedDateTime;

@Entity
@Table(name = "Chat_Entries")
public class ChatEntries {

    @Column(name = "Entry_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Id Long id;

    @Column(name = "Content")
    private String content;

    @Convert(converter = ZonedDateTimeConverter.class)
    @Column(name = "Timestamp")
    private ZonedDateTime timestamp;

    @ManyToOne(optional = true)
    @JoinColumn(name = "chat_id", nullable = true)
    private Chat chat;

    public ChatEntries() {
    }
}
