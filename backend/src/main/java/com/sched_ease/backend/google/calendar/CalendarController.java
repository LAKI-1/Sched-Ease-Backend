package com.sched_ease.backend.google.calendar;

import com.google.api.services.calendar.model.Event;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CalendarController {

    private final GoogleCalendarService GooglecalendarService;

    public CalendarController(GoogleCalendarService calendarService) {
        this.GooglecalendarService = calendarService;
    }

    @GetMapping("/events")
    public List<Event> getFilteredEvents(@RequestParam(required = false) String filter) throws Exception {
        List<Event> events = GooglecalendarService.getUpcomingEvents();

        if (filter != null) {
            return events.stream()
                    .filter(event -> event.getSummary() != null && event.getSummary().toLowerCase().contains(filter.toLowerCase()))
                    .collect(Collectors.toList());
        }

        return events;
    }

    @GetMapping("/events-short")
    public List<Map<String, Object>> getSummarizedEvents(@RequestParam(required = false) String filter) throws Exception {
        List<Event> events = GooglecalendarService.getUpcomingEvents();

        // Filter events if a filter is provided
        if (filter != null) {
            events = events.stream()
                    .filter(event -> event.getSummary() != null && event.getSummary().toLowerCase().contains(filter.toLowerCase()))
                    .collect(Collectors.toList());
        }

        // Map events to summarized information
        return events.stream()
                .map(event -> {
                    Map<String, Object> summary = new HashMap<>();
                    summary.put("title", event.getSummary());
                    summary.put("startTime", event.getStart().getDateTime() != null
                            ? event.getStart().getDateTime().toString()
                            : event.getStart().getDate().toString());
                    summary.put("creator", event.getCreator() != null ? event.getCreator().getEmail() : "Unknown");
                    summary.put("description", event.getDescription() != null ? event.getDescription() : "No description available");
                    summary.put("attendees", event.getAttendees() != null
                            ? event.getAttendees().stream().map(attendee -> attendee.getEmail()).collect(Collectors.toList())
                            : Collections.emptyList());
                    return summary;
                })
                .collect(Collectors.toList());
    }


    @GetMapping("/appointments")
    public List<Event> getUserAppointments() throws Exception {
        return GooglecalendarService.getUserAppointments();
    }

}
