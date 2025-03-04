package com.sched_ease.backend.google.calendar;


import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.calendar.CalendarScopes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Collections;
import java.util.List;

@Configuration
public class GoogleCalendarConfig {

    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
//    private static final String TOKENS_DIRECTORY_PATH = "tokens";
    private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR_READONLY);
//    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";

    @Bean
    public Credential googleCredential() throws Exception {
//        InputStream in = getClass().getResourceAsStream(CREDENTIALS_FILE_PATH);
//        if (in == null) {
//            throw new Exception("Resource not found: " + CREDENTIALS_FILE_PATH);
//        }
//
//        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        Dotenv dotenv = Dotenv.configure()
                .filename("secrets.env") // Explicitly load secrets.env
                .ignoreIfMissing()
                .load();
        String clientId = dotenv.get("GOOGLE_CLIENT_ID");
        String clientSecret = dotenv.get("GOOGLE_CLIENT_SECRET");

        if (clientId == null || clientSecret == null) {
            throw new IOException("Missing Google OAuth credentials in environment variables");
        }

        // Build GoogleClientSecrets JSON string
        String jsonSecrets = String.format("{\"installed\": {\"client_id\": \"%s\", \"client_secret\": \"%s\"}}", clientId, clientSecret);

        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new StringReader(jsonSecrets));

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JSON_FACTORY,
                clientSecrets,
                SCOPES
        )
                .setAccessType("offline")
                .build();

        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    @Bean
    public com.google.api.services.calendar.Calendar googleCalendar(Credential credential) throws Exception {
        return new com.google.api.services.calendar.Calendar.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JSON_FACTORY,
                credential
        )
                .setApplicationName("Google Calendar API Spring Boot Integration")
                .build();
    }

}
