package com.sched_ease.backend.database.repositories;

import com.sched_ease.backend.database.entities.Hall;
import com.sched_ease.backend.database.entities.TimeTableEntries;
import com.sched_ease.backend.database.entities.TutorialGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TimeTableEntriesRepository extends JpaRepository<TimeTableEntries, Long> {
    @Query("SELECT t FROM TimeTableEntries t WHERE t.dayOfWeek = :dayOfWeek AND t.hall = :hall AND t.lectureOrTutorial = :lectureOrTutorial")
    List<TimeTableEntries> sessionDetails(
            @Param("dayOfWeek") String dayOfWeek,
            @Param("hall") Hall hall,
            @Param("lectureOrTutorial") String lectureOrTutorial
    );

    List<TimeTableEntries> findByTutorialGroup(TutorialGroup tutorialGroup);
}