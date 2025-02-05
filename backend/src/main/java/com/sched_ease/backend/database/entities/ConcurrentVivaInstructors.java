package com.sched_ease.backend.database.entities;

import com.sched_ease.backend.database.emendables.ConcurrentVivaInstructorId;
import jakarta.persistence.*;

@Entity
@Table(name = "Concurrent_Viva_Instructors")
public class ConcurrentVivaInstructors {

    @EmbeddedId
    private ConcurrentVivaInstructorId id;

    @ManyToOne
    @MapsId("lecturerId")  // Maps FK to Lecturer entity
    @JoinColumn(name = "Lecturer_Id", nullable = false)
    private Lecturer lecturer;

    @ManyToOne
    @MapsId("concurrentVivaId")  // Maps FK to ConcurrentViva entity
    @JoinColumn(name = "Concurrent_Viva_Id", nullable = false)
    private ConcurrentViva concurrentViva;

    @Column(name = "timestamp", nullable = false)
    private String timestamp;

    public ConcurrentVivaInstructors() {}

    public ConcurrentVivaInstructors(Lecturer lecturer, ConcurrentViva concurrentViva, String timestamp) {
        this.id = new ConcurrentVivaInstructorId(lecturer.getId(), concurrentViva.getId());
        this.lecturer = lecturer;
        this.concurrentViva = concurrentViva;
        this.timestamp = timestamp;
    }

    // Getters and Setters
}
