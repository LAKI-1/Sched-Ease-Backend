package com.sched_ease.backend.database.entities;

import com.sched_ease.backend.database.emendables.ConcurrentVivaInstructorId;
import com.sched_ease.backend.database.emendables.SDGPLecturersId;
import jakarta.persistence.*;

@Entity
@Table(name = "SDGP_Lecturers")
public class SDGPLecturers {

    @EmbeddedId
    private SDGPLecturersId id;

    @ManyToOne
    @MapsId("lecturerId")  // Maps FK to Lecturer entity
    @JoinColumn(name = "Lecturer_Id", nullable = false)
    private Lecturer lecturer;

    @ManyToOne
    @MapsId("entryId")  // Maps FK to ConcurrentViva entity
    @JoinColumn(name = "Entry_Id", nullable = false)
    private TimeTableEntries timeTableEntries;

    @Column(name = "Lecture/Tutorial", nullable = false)
    private String lectureOrTutorial;

    public SDGPLecturers() {}

    public SDGPLecturers(Lecturer lecturer, TimeTableEntries timeTableEntries, String lectureOrTutorial) {
        this.id = new SDGPLecturersId(lecturer.getId(), timeTableEntries.getId());
        this.lecturer = lecturer;
        this.timeTableEntries = timeTableEntries;
        this.lectureOrTutorial = lectureOrTutorial;
    }
    // Getters and Setters
}
