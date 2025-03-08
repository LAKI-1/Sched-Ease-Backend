package com.sched_ease.backend.services;

import com.sched_ease.backend.database.entities.*;
import com.sched_ease.backend.database.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

@Service
public class SessionService {

    @Autowired
    private TimeTableEntriesRepository timeTableEntriesRepository;

    @Autowired
    private LecturerRepository lecturerRepository;

    @Autowired
    private TutorialGroupRepository tutorialGroupRepository;

    @Autowired
    private TimeTableRepository timeTableRepository;

    @Autowired
    private HallRepository hallRepository;

    public void addSessionToGroup(Long groupId, Set<Long> lecturerIds, Long hallId, String level, String course, String dayOfWeek,
                                  String startTime, String endTime, String lectureOrTutorial, Long timeTableId) {

        // Find all lecturers
        List<Lecturer> lecturers = lecturerRepository.findAllById(lecturerIds);
        if (lecturers.isEmpty()) {
            throw new RuntimeException("No valid lecturers found.");
        }

        // Find the tutorial group
        System.out.println("Group ID received: " + groupId);
        TutorialGroup group = tutorialGroupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));


        // Find the hall
        Hall hall = hallRepository.findById(hallId)
                .orElseThrow(() -> new RuntimeException("Hall not found"));

        // Find the timetable
        TimeTable timeTable = timeTableRepository.findById(timeTableId)
                .orElseThrow(() -> new RuntimeException("TimeTable not found"));

        // Convert startTime and endTime safely
        ZonedDateTime startDateTime;
        ZonedDateTime endDateTime;
        try {
            startDateTime = ZonedDateTime.parse(startTime);
            endDateTime = ZonedDateTime.parse(endTime);
        } catch (Exception e) {
            throw new RuntimeException("Invalid date-time format. Use ISO format (e.g., 2025-03-05T08:30:00Z)");
        }

        // Check for overlapping tutorials
        if (lectureOrTutorial.equalsIgnoreCase("Tutorial")) {
            boolean overlapExists = checkForOverlappingTutorials(dayOfWeek, startDateTime, endDateTime, hall);
            if (overlapExists) {
                throw new RuntimeException("Overlapping tutorials in the same classroom.");
            }
        }

        // Create and save TimeTableEntry
        TimeTableEntries entry = new TimeTableEntries();
        entry.setLevel(level);
        entry.setCourse(course);
        entry.setDayOfWeek(dayOfWeek);
        entry.setStartTime(startDateTime);
        entry.setEndTime(endDateTime);
        entry.setLectureOrTutorial(lectureOrTutorial);
        entry.setLecturers(Set.copyOf(lecturers));
        entry.setHall(hall);
        entry.setTimeTable(timeTable);
        entry.setTutorialGroup(group);

        timeTableEntriesRepository.save(entry);
    }

    private boolean checkForOverlappingTutorials(String dayOfWeek, ZonedDateTime startTime, ZonedDateTime endTime, Hall hall) {
        List<TimeTableEntries> existingEntries = timeTableEntriesRepository
                .sessionDetails(dayOfWeek, hall, "Tutorial");

        return existingEntries.stream().anyMatch(entry ->
                entry.getStartTime().isBefore(endTime) && entry.getEndTime().isAfter(startTime));
    }
}