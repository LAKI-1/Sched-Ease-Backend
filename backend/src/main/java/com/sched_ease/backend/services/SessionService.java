package com.sched_ease.backend.services;

import com.sched_ease.backend.database.entities.*;
import com.sched_ease.backend.database.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Set;

@Service
public class SessionService {

    @Autowired
    private TimeTableRepository timeTableRepository;

    @Autowired
    private TutorialGroupRepository tutorialGroupRepository;

    @Autowired
    private LecturerRepository lecturerRepository;

    @Autowired
    private HallRepository hallRepository;

    // Method to add a session to a group
    @Transactional
    public void addSessionToGroup(
            Long groupId,
            Set<Long> lecturerIds,
            Long hallId,
            String level,
            String course,
            String dayOfWeek,
            String startTimeStr,
            String endTimeStr,
            String lectureOrTutorial,
            Long timeTableId
    ) {
        // Fetch the necessary entities
        TutorialGroup group = tutorialGroupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Tutorial group not found with ID: " + groupId));

        TimeTable timeTable;
        if (timeTableId != null) {
            timeTable = timeTableRepository.findById(timeTableId)
                    .orElseThrow(() -> new RuntimeException("TimeTable not found with ID: " + timeTableId));
        } else {
            throw new RuntimeException("TimeTable ID must be provided");
        }

        Hall hall = hallRepository.findById(hallId)
                .orElseThrow(() -> new RuntimeException("Hall not found with ID: " + hallId));

        // Parse time strings to ZonedDateTime objects
        ZonedDateTime startTime = parseTimeString(startTimeStr);
        ZonedDateTime endTime = parseTimeString(endTimeStr);

        if (startTime == null) {
            throw new RuntimeException("Start time cannot be null");
        }

        if (endTime == null) {
            throw new RuntimeException("End time cannot be null");
        }

        // Create and set up the TimeTableEntries object
        TimeTableEntries entry = new TimeTableEntries();
        entry.setLevel(level);
        entry.setCourse(course);
        entry.setDayOfWeek(dayOfWeek);
        entry.setStartTime(startTime);
        entry.setEndTime(endTime);
        entry.setLectureOrTutorial(lectureOrTutorial);
        entry.setTimeTable(timeTable);
        entry.setTutorialGroup(group);

        // Set up Hall relationship
        entry.setHall(hall);

        // Set up Lecturer relationships
        for (Long lecturerId : lecturerIds) {
            Lecturer lecturer = lecturerRepository.findById(lecturerId)
                    .orElseThrow(() -> new RuntimeException("Lecturer not found with ID: " + lecturerId));
            entry.getLecturers().add(lecturer);
            lecturer.getTimeTableEntries().add(entry);
        }

        // Add the entry to the timetable
        timeTable.getTimeTableEntries().add(entry);

        // Save is not needed here as @Transactional will handle the persistence
    }

    // Helper method to parse time strings
    private ZonedDateTime parseTimeString(String timeStr) {
        if (timeStr == null || timeStr.isEmpty()) {
            return null;
        }

        try {
            // Try to parse as HH:mm format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime localTime = LocalTime.parse(timeStr, formatter);

            // Create a ZonedDateTime with today's date and the parsed time
            LocalDate today = LocalDate.now();
            return ZonedDateTime.of(today, localTime, ZoneId.systemDefault());
        } catch (DateTimeParseException e) {
            try {
                // If HH:mm parsing fails, try ISO format
                return ZonedDateTime.parse(timeStr);
            } catch (DateTimeParseException e2) {
                throw new RuntimeException("Invalid time format: " + timeStr);
            }
        }
    }
}