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

    // Getting all the repositories that are needed to make the service class working and running

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SDGPGroupRepository sdgpGroupRepository;

    @Autowired
    private SDGPStudentRepository sdgpStudentRepository;

    @Autowired
    private EntityManager entityManager;

    // Retrieving all the registered teams
    public List<SDGPGroup> getAllTeams() {
        return sdgpGroupRepository.findAll();
    }

    @Transactional
    public SDGPGroup registerTeam(TeamRegistrationRequest request) {
        try {
            // Validating the team size to make sure there will be only 6 students
            if (request.getStudentIds().size() != 6) {
                logger.error("Invalid team size: {}", request.getStudentIds().size());
                throw new IllegalArgumentException("Team must have exactly 6 members");
            }

            // Convert String IDs into Long and after that to fetch all students
            List<Long> studentIds = request.getStudentIds().stream()
                    .map(Long::valueOf)
                    .collect(Collectors.toList());

            logger.info("Fetching students with IDs: {}", studentIds);
            List<Student> students = studentRepository.findAllById(studentIds);

            if (students.size() != 6) {
                logger.error("Not all students found. Expected 6, found: {}", students.size());
                throw new MemberNotFoundException("One or more students not found");
            }

            // Determining the course based on students' courses to make sure to give them relevant ids due to there are software engineering students and computer science students.
            Map<String, Long> courseCounts = students.stream()
                    .map(Student::getCourse)
                    .collect(Collectors.groupingBy(
                            course -> course.toLowerCase().contains("software") ? "SE" : "CS",
                            Collectors.counting()
                    ));

            String teamCourse = courseCounts.getOrDefault("SE", 0L) > courseCounts.getOrDefault("CS", 0L) ? "SE" : "CS";
            logger.info("Determined team course: {}", teamCourse);

            // Getting the next group number to make sure it goes on order from 1 to the number of groups that are registered.
            Integer maxGroupNo = sdgpGroupRepository.findMaxGroupNumberByCourse(teamCourse);
            int nextGroupNo = (maxGroupNo == null ? 0 : maxGroupNo) + 1;
            logger.info("Assigned group number: {}-{}", teamCourse, nextGroupNo);

            // Creating new SDGPGroup for SDGPStudents
            final SDGPGroup group = new SDGPGroup();
            group.setCourse(teamCourse);
            group.setGroupNo(nextGroupNo);
            group.setRegistrationStatus(false); // Waiting for lecturer approval for approving the registered teams

            // Saving the group first
            logger.info("Saving new SDGP group");
            SDGPGroup savedGroup = sdgpGroupRepository.save(group);
            logger.info("Successfully saved SDGP group with ID: {}", savedGroup.getId());

            // Converting normal enrolled students to SDGPStudents and add to group
            List<SDGPStudent> sdgpStudents = new ArrayList<>();
            for (Student student : students) {
                // Checking if the student is already an SDGPStudent
                if (student instanceof SDGPStudent) {
                    throw new IllegalArgumentException("Student " + student.getId() + " is already registered as an SDGP student");
                }

                // Before registering need to check if this student is already registered with another SDGP group
                if (sdgpStudentRepository.existsById(student.getId())) {
                    throw new IllegalArgumentException("Student " + student.getId() + " is already registered in an SDGP group");
                }

                // Create new SDGPStudent using SQL
                entityManager.createNativeQuery(
                                "INSERT INTO sdgp_student (student_id, sdgp_leader_flag, student_course, student_email, student_name, sdgp_group_no) VALUES (?, ?, ?, ?, ?, ?)")
                        .setParameter(1, student.getId())
                        .setParameter(2, false)
                        .setParameter(3, student.getCourse())
                        .setParameter(4, student.getEmail())
                        .setParameter(5, student.getName())
                        .setParameter(6, savedGroup.getId())
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

    @Transactional
    public SDGPGroup approveTeam(Long teamId, Long LeaderId){
        try{
            // Finding the registered team
            SDGPGroup group = sdgpGroupRepository.findById(teamId).orElseThrow(() -> new RuntimeException("Team not found"));

            // Verifying whether the team is in pending for approval
            if (group.isRegistrationStatus()){
                throw new IllegalArgumentException("Team is already registered");
            }

            // Finding the leader of the Team
            SDGPStudent leader = group.getStudents().stream()
                    .filter(student -> student.getId().equals(LeaderId))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Leader not found in team members"));

            // Setting the Leader Flag
            leader.setLeaderFlag(true);
            sdgpStudentRepository.save(leader);

            // Approving the team
            group.setRegistrationStatus(true);
            SDGPGroup savedGroup = sdgpGroupRepository.save(group);

            logger.info("Successfully approved team {} with leader {}", teamId, LeaderId);
            return savedGroup;
        }
        catch (Exception e){
            logger.error("Error during team approval", e);
            throw new RuntimeException("Failed to approve team: " + e.getMessage(), e);
        }
    }

    @Transactional
    public SDGPGroup rejectTeam(Long teamId){
        try{
            // Finding the registered team
            SDGPGroup group = sdgpGroupRepository.findById(teamId).orElseThrow(() -> new RuntimeException("Team not found"));

            // Verifying whether the team is in pending for approval
            if (group.isRegistrationStatus()){
                throw new IllegalArgumentException("Team is already registered");
            }

            // Setting team rejected status
            group.setRegistrationStatus(false);
            SDGPGroup savedGroup = sdgpGroupRepository.save(group);

            logger.info("Successfully rejected team {}", teamId);
            return savedGroup;
        }
        catch (Exception e){
            logger.error("Error during team approval", e);
            throw new RuntimeException("Failed to reject team: " + e.getMessage(), e);
        }
    }
}