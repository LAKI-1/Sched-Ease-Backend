package com.sched_ease.backend.database.emendables;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ConcurrentVivaInstructorId implements Serializable {

    @Column(name = "Lecturer_Id")
    private Long lecturerId;

    @Column(name = "Concurrent_Viva_Id")
    private Long concurrentVivaId;

    public ConcurrentVivaInstructorId() {}

    public ConcurrentVivaInstructorId(Long lecturerId, Long concurrentVivaId) {
        this.lecturerId = lecturerId;
        this.concurrentVivaId = concurrentVivaId;
    }

    // Getters, Setters, equals() and hashCode() (required for composite keys)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConcurrentVivaInstructorId that = (ConcurrentVivaInstructorId) o;
        return Objects.equals(lecturerId, that.lecturerId) &&
                Objects.equals(concurrentVivaId, that.concurrentVivaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lecturerId, concurrentVivaId);
    }
}
