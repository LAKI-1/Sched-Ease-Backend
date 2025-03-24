package com.sched_ease.backend.controllers;


import com.sched_ease.backend.database.entities.Lecturer;
import com.sched_ease.backend.database.repositories.LecturerRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/lecturers")
@CrossOrigin(origins = "http://localhost:3000")
public class LecturerController {
    private static final Logger logger = LoggerFactory.getLogger(LecturerController.class);

    @Autowired
    private LecturerRepository lecturerRepository;

    @PersistenceContext
    private EntityManager entityManager;

    // Getting all the lecturers
    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getAllLecturers() {
        try {
            List<Map<String, Object>> lecturers = lecturerRepository.findAll().stream().map(lecturer -> {
                Map<String, Object> map = new HashMap<>();
                        map.put("id", lecturer.getId());
                        map.put("name", lecturer.getName());
                map.put("nameShort", lecturer.getNameShort());
                 map.put("email", lecturer.getEmail());
                return map;
            }).collect(Collectors.toList());
            return ResponseEntity.ok(lecturers);
        } catch (Exception e) {
            logger.error("Error fetching lecturers", e);
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Adding a new lecturer
    @PostMapping
    @Transactional
    public ResponseEntity<?> createLecturer(@RequestBody Map<String, String> request) {
        try {
            // Validating the required fields
            if (!request.containsKey("name") || !request.containsKey("nameShort") || !request.containsKey("email")) {
                return ResponseEntity.badRequest()
                        .body(Map.of("error", "Name, short name, and email are required"));
            }

            // Get the maximum ID currently in use
            Long maxId = entityManager.createQuery(
                            "SELECT COALESCE(MAX(l.id), 0) FROM Lecturer l", Long.class)
                    .getSingleResult();

            // Create new lecturer with next ID
            Lecturer lecturer = new Lecturer();
            lecturer.setId(maxId + 1); // Set the next available ID
            lecturer.setName(request.get("name"));
            lecturer.setNameShort(request.get("nameShort"));
            lecturer.setEmail(request.get("email"));

            // After creating a new lecturer save the new lecturer
            lecturer = lecturerRepository.save(lecturer);

            // Create response
            Map<String, Object> response = new HashMap<>();
            response.put("id", lecturer.getId());
            response.put("name", lecturer.getName());
            response.put("nameShort", lecturer.getNameShort());
            response.put("email", lecturer.getEmail());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error creating lecturer", e);
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Failed to create lecturer: " + e.getMessage()));
        }
    }
}