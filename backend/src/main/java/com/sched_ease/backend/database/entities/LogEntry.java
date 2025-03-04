package com.sched_ease.backend.database.entities;


import com.sched_ease.backend.database.converters.ZonedDateTimeConverter;
import jakarta.persistence.*;

import java.time.ZonedDateTime;

@Entity
@Table(name = "Log_entry")
public class LogEntry {

    @Column(name = "Log_Entry_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Id Long id;

    @Column(name = "Entry")
    private String entry;

    @Convert(converter = ZonedDateTimeConverter.class)
    @Column(name = "Timestamp")
    private ZonedDateTime timestamp;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "SDGP_Group_No", nullable = true)
    private SDGPGroup sdgpGroup;

    public LogEntry() {}
}
