package com.sched_ease.backend.database.entities;


import com.sched_ease.backend.database.converters.ZonedDateTimeConverter;
import jakarta.persistence.*;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TimeTable")
public class TimeTable {

    @Column(name = "TimeTable_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Id Long id;

    @Convert(converter = ZonedDateTimeConverter.class)
    @Column(name = "Date_Of_Creation")
    private ZonedDateTime dateOfCreation;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "Created_By", nullable = true)
    private ExternalAdministrator administrator;

    @OneToMany(mappedBy = "timeTable", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<TimeTableEntries> timeTableEntries = new ArrayList<>();

    public TimeTable() {
    }
}
