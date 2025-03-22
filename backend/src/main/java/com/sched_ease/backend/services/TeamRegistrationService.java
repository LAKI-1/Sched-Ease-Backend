package com.sched_ease.backend.services;

import jakarta.persistence.EntityManager;
import com.sched_ease.backend.database.entities.SDGPGroup;
import com.sched_ease.backend.database.entities.SDGPStudent;
import com.sched_ease.backend.database.entities.Student;
import com.sched_ease.backend.database.repositories.SDGPGroupRepository;
import com.sched_ease.backend.database.repositories.SDGPStudentRepository;
import com.sched_ease.backend.database.repositories.StudentRepository;
import com.sched_ease.backend.dto.TeamRegistrationRequest;
import com.sched_ease.backend.exception.MemberNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TeamRegistrationService {
    private static final Logger logger = LoggerFactory.getLogger(TeamRegistrationService.class);

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SDGPGroupRepository sdgpGroupRepository;

    @Autowired
    private SDGPStudentRepository sdgpStudentRepository;

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public SDGPGroup registerTeam(TeamRegistrationRequest request) {
        try {
            // Validate team size
            if (request.getStudentIds().size() != 6) {
                logger.error("Invalid team size: {}", request.getStudentIds().size());
                throw new IllegalArgumentException("Team must have exactly 6 members");
            }

            // Convert String IDs to Long and fetch all students
            List<Long> studentIds = request.getStudentIds().stream()
                    .map(Long::valueOf)
                    .collect(Collectors.toList());

            logger.info("Fetching students with IDs: {}", studentIds);
            List<Student> students = studentRepository.findAllById(studentIds);

            if (students.size() != 6) {
                logger.error("Not all students found. Expected 6, found: {}", students.size());
                throw new MemberNotFoundException("One or more students not found");
            }

            // Determine course based on students' courses
            Map<String, Long> courseCounts = students.stream()
                    .map(Student::getCourse)
                    .collect(Collectors.groupingBy(
                            course -> course.toLowerCase().contains("software") ? "SE" : "CS",
                            Collectors.counting()
                    ));

            String teamCourse = courseCounts.getOrDefault("SE", 0L) > courseCounts.getOrDefault("CS", 0L) ? "SE" : "CS";
            logger.info("Determined team course: {}", teamCourse);

            // Get next group number
            Integer maxGroupNo = sdgpGroupRepository.findMaxGroupNumberByCourse(teamCourse);
            int nextGroupNo = (maxGroupNo == null ? 0 : maxGroupNo) + 1;
            logger.info("Assigned group number: {}-{}", teamCourse, nextGroupNo);

            // Create new SDGPGroup
            final SDGPGroup group = new SDGPGroup();
            group.setCourse(teamCourse);
            group.setGroupNo(nextGroupNo);
            group.setRegistrationStatus(false); // Waiting for lecturer approval

            // Save the group first
            logger.info("Saving new SDGP group");
            SDGPGroup savedGroup = sdgpGroupRepository.save(group);
            logger.info("Successfully saved SDGP group with ID: {}", savedGroup.getId());

            // Convert students to SDGPStudents and add to group
            List<SDGPStudent> sdgpStudents = new ArrayList<>();
            for (Student student : students) {
                // Check if student is already an SDGPStudent
                if (student instanceof SDGPStudent) {
                    throw new IllegalArgumentException("Student " + student.getId() + " is already registered as an SDGP student");
                }

                // First check if this student is already in an SDGP group
                if (sdgpStudentRepository.existsById(student.getId())) {
                    throw new IllegalArgumentException("Student " + student.getId() + " is already registered in an SDGP group");
                }

                // Create new SDGPStudent using SQL
                entityManager.createNativeQuery(
                                "INSERT INTO sdgp_student (student_id, sdgp_leader_flag, sdgp_group_no) VALUES (?, ?, ?)")
                        .setParameter(1, student.getId())
                        .setParameter(2, false)
                        .setParameter(3, savedGroup.getId())
                        .executeUpdate();

                // Refresh the student from the database as an SDGPStudent
                entityManager.flush();
                entityManager.clear();
                SDGPStudent sdgpStudent = entityManager.find(SDGPStudent.class, student.getId());
                sdgpStudents.add(sdgpStudent);

                logger.debug("Created SDGP student entry for student ID: {}", student.getId());
            }

            // Set the students list in the group
            savedGroup.setStudents(sdgpStudents);

            // Save the group again to update the relationships
            savedGroup = sdgpGroupRepository.save(savedGroup);

            // Return the saved group
            return savedGroup;
        } catch (Exception e) {
            logger.error("Error during team registration", e);
            throw new RuntimeException("Failed to register team: " + e.getMessage(), e);
        }
    }
}