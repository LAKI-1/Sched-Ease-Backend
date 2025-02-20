package com.sched_ease.backend.database.entities;


import com.sched_ease.backend.database.converters.ZonedDateTimeConverter;
import jakarta.persistence.*;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Viva_Session")
public class VivaSession {

    @Column(name = "Viva_Session_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Id Long id;

    @Convert(converter = ZonedDateTimeConverter.class)
    @Column(name = "Timestamp")
    private ZonedDateTime timestamp;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "Concurrent_Viva_Id", nullable = true)
    private ConcurrentViva concurrentViva;

    @Column(name = "Duration")
    private int duration;

    @OneToMany(mappedBy = "vivaSession", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<SDGPGroup> sDGPGroups = new ArrayList<>();

    public VivaSession() {
    }
}
