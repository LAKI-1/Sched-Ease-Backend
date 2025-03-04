package com.sched_ease.backend.database.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) // Creates separate tables for each subclass
@Table(name = "Chat")
public class Chat {

    @Column(name = "Chat_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Id Long id;

    @Column(name = "Chat_Name")
    private String name;

    @OneToMany(mappedBy = "chat", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @Column(name = "Chat_Entries")
    private List<ChatEntries> entries = new ArrayList<>();

    public Chat() {
    }
}
