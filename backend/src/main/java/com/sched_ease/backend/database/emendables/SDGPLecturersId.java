package com.sched_ease.backend.database.emendables;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SDGPLecturersId implements Serializable {

    @Column(name = "Lecturer_id")
    private Long lecturerId;

    @Column(name = "Entry_Id")
    private Long entryId;

    public SDGPLecturersId() {}

    public SDGPLecturersId(Long lecturerId, Long entryId) {
        this.lecturerId = lecturerId;
        this.entryId = entryId;
    }

    // Getters, Setters, equals() and hashCode() (required for composite keys)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SDGPLecturersId that = (SDGPLecturersId) o;
        return Objects.equals(lecturerId, that.lecturerId) &&
                Objects.equals(entryId, that.entryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lecturerId, entryId);
    }
}
