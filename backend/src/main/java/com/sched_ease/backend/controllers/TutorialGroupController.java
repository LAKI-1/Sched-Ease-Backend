package com.sched_ease.backend.controllers;

import com.sched_ease.backend.database.entities.TutorialGroup;
import com.sched_ease.backend.database.repositories.TutorialGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tutorial-groups")
@CrossOrigin(origins = "*")
public class TutorialGroupController {

    @Autowired
    private TutorialGroupRepository tutorialGroupRepository;

    @GetMapping
    public ResponseEntity<List<TutorialGroupDTO>> getAllTutorialGroups() {
        List<TutorialGroup> groups = tutorialGroupRepository.findAll();
        List<TutorialGroupDTO> dtos = groups.stream()
                .map(group -> new TutorialGroupDTO(
                        group.getId(),
                        group.getGroupNo(),
                        group.getCourse(),
                        group.getSemester()
                ))
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    public static class TutorialGroupDTO {
        private Long id;
        private String groupNo;
        private String course;
        private String semester;

        public TutorialGroupDTO(Long id, String groupNo, String course, String semester) {
            this.id = id;
            this.groupNo = groupNo;
            this.course = course;
            this.semester = semester;
        }

        public Long getId() {
            return id;
        }

        public String getGroupNo() {
            return groupNo;
        }

        public String getCourse() {
            return course;
        }

        public String getSemester() {
            return semester;
        }
    }
}