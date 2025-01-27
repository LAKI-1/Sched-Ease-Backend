package com.sched_ease.backend.google.calendar;

import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoogleCalendarService {

    private final Calendar googleCalendar;

    public GoogleCalendarService(Calendar googleCalendar) {
        this.googleCalendar = googleCalendar;
    }

    public List<Event> getUpcomingEvents() throws Exception {
        Events events = googleCalendar.events().list("primary")
                .setMaxResults(20)
                .setTimeMin(new com.google.api.client.util.DateTime(System.currentTimeMillis()))
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();

        return events.getItems();
    }

    public List<Event> getUpcomingEvents(int noOfResults) throws Exception {
        Events events = googleCalendar.events().list("primary")
                .setMaxResults(noOfResults)
                .setTimeMin(new com.google.api.client.util.DateTime(System.currentTimeMillis()))
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();

        return events.getItems();
    }

    public List<Event> getUserAppointments() throws Exception {
        // Fetch all events from the primary calendar
        Events events = googleCalendar.events().list("primary")
                .setTimeMin(new com.google.api.client.util.DateTime(System.currentTimeMillis()))
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();

        // Filter for appointment sessions
        return events.getItems().stream()
                .filter(event -> event.getSummary() != null && event.getSummary().toLowerCase().contains("appointment"))
                .collect(Collectors.toList());
    }
}
