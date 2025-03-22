package com.sched_ease.backend.dto;

import java.util.Set;

public class SessionRequestDTO {

    private Set<Long> lecturerIds;
    private Long hallId;
    private String level;
    private String course;
    private String dayOfWeek;
    private String startTime;
    private String endTime;
    private String lectureOrTutorial;
    private Long timeTableId;

    public Set<Long> getLecturerIds() {
        return lecturerIds;
    }

    public void setLecturerIds(Set<Long> lecturerIds) {
        this.lecturerIds = lecturerIds;
    }

    public Long getHallId() {
        return hallId;
    }

    public void setHallId(Long hallId) {
        this.hallId = hallId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getLectureOrTutorial() {
        return lectureOrTutorial;
    }

    public void setLectureOrTutorial(String lectureOrTutorial) {
        this.lectureOrTutorial = lectureOrTutorial;
    }

    public Long getTimeTableId() {
        return timeTableId;
    }

    public void setTimeTableId(Long timeTableId) {
        this.timeTableId = timeTableId;
    }
}
