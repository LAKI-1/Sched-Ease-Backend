package com.sched_ease.backend.database.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "Log_entry")
public class LogEntry {

    @Column(name = "Log_Entry_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Id Long id;

    @Column(name = "Entry")
    private String entry;

    @Column(name = "Timestamp")
    private String timestamp;

    @ManyToOne
    @JoinColumn(name = "SDGP_Group_No")
    private SDGPGroup sdgpGroup;

    public LogEntry() {}
}
