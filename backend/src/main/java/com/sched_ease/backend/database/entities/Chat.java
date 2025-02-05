package com.sched_ease.backend.database.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Chat")
public class Chat {

    @Column(name = "Chat_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Id Long id;

    @Column(name = "Chat_Name")
    private String name;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChatEntries> entries = new ArrayList<>();

    public Chat() {
    }
}
