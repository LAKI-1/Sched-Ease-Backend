package com.sched_ease.backend.google.calendar;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;

import java.io.*;
import java.security.GeneralSecurityException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.sched_ease.backend.database.entities.FeedbackSession;
import com.sched_ease.backend.database.entities.SDGPStudent;
import org.springframework.stereotype.Service;

@Service
public class GoogleCalendarService {
    private static final String APPLICATION_NAME = "SDGP Backend";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";
    private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR_EVENTS);
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json"; // Ensure this is in `src/main/resources/`

    /**
     * Authenticate application and return Google Calendar Service.
     */
    private static Credential getCredentials(HttpTransport HTTP_TRANSPORT) throws IOException {
        InputStream in = GoogleCalendarService.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();

        return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
    }

    public Boolean inviteByFeedbackSession(FeedbackSession feedbackSession){

        try {
            if (feedbackSession != null) {
                String inviterEmail = feedbackSession.getsDGPGroup().getGroupLeader().getEmail();
                List<String> invitees = new ArrayList<>();
                String eventTitle = feedbackSession.getTitle();
                String eventDescription = "Feedback session booking";
                ZonedDateTime startTime = feedbackSession.getStarDateTime();
                ZonedDateTime endTime = feedbackSession.getEndDateTime();

                for (SDGPStudent member : feedbackSession.getsDGPGroup().getMembersOnly()) {
                    invitees.add(member.getEmail());
                }

                createEvent(inviterEmail, invitees, eventTitle, eventDescription, startTime, endTime);

            } else {
                return false;
            }
            return false;
        } catch (GeneralSecurityException | IOException e) {
//            throw new RuntimeException(e);
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Creates and invites participants to a Google Calendar event.
     */
    public String createEvent(String inviterEmail, List<String> invitees, String eventTitle,
                              String eventDescription, ZonedDateTime startTime, ZonedDateTime endTime)
            throws GeneralSecurityException, IOException {
        HttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();

        Event event = new Event()
                .setSummary(eventTitle)
                .setDescription(eventDescription); // Will be replaced with formatted text

        // Convert ZonedDateTime to RFC3339 DateTime format required by Google Calendar API
        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

        EventDateTime start = new EventDateTime()
                .setDateTime(new DateTime(startTime.format(formatter))) // Convert ZonedDateTime to Google API format
                .setTimeZone(startTime.getZone().toString());
        event.setStart(start);

        EventDateTime end = new EventDateTime()
                .setDateTime(new DateTime(endTime.format(formatter))) // Convert ZonedDateTime to Google API format
                .setTimeZone(endTime.getZone().toString());
        event.setEnd(end);

        List<EventAttendee> attendees = new ArrayList<>();
        for (String email : invitees) {
            attendees.add(new EventAttendee().setEmail(email));
        }
        event.setAttendees(attendees);

        // Format description using the template
        String formattedBody = formatInviteBody(
                "Multiple invitees", eventTitle, eventDescription,
                startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm z")),
                endTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm z")),
                "Online Meeting", "Organizer Name", inviterEmail, "https://meet.google.com/some-link"
        );
        event.setDescription(formattedBody);

        // Insert event into Google Calendar
        event = service.events().insert("primary", event).execute();
        return "Event created: " + event.getHtmlLink();
    }


    /**
     * Reads the invite template file and replaces placeholders with actual values.
     */
    private String formatInviteBody(String inviteeEmail, String eventTitle, String eventDescription,
                                    String startDateTime, String endDateTime, String location,
                                    String organizerName, String organizerEmail, String meetingLink) throws IOException {
        String templatePath = "src/main/resources/invite_template.txt";
        String template = new String(Files.readAllBytes(Paths.get(templatePath)), StandardCharsets.UTF_8);

        return template.replace("{invitee}", inviteeEmail)
                .replace("{eventTitle}", eventTitle)
                .replace("{eventDescription}", eventDescription)
                .replace("{startDateTime}", startDateTime)
                .replace("{endDateTime}", endDateTime)
                .replace("{location}", location != null ? location : "N/A")
                .replace("{organizerName}", organizerName)
                .replace("{organizerEmail}", organizerEmail)
                .replace("{meetingLink}", meetingLink != null ? meetingLink : "N/A");
    }

}


