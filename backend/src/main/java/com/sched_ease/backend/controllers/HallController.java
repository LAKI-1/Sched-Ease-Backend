package com.sched_ease.backend.controllers;

import com.sched_ease.backend.database.repositories.HallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/halls")
@CrossOrigin(origins = "*")
public class HallController {

    @Autowired
    private HallRepository hallRepository;

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getAllHalls() {
        try {
            List<Map<String, Object>> halls = hallRepository.findAll().stream()
                    .map(hall -> {
                        Map<String, Object> map = new HashMap<>();
                        map.put("id", hall.getId());
                        map.put("name", hall.getName());
                        map.put("building", hall.getBuilding());
                        map.put("capacity", hall.getCapacity());
                        return map;
                    })
                    .collect(Collectors.toList());
            return ResponseEntity.ok(halls);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
} 