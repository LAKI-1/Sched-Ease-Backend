package com.sched_ease.backend.utilities;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Instant;

/**
 * Utility class for handling JSON responses in a Spring Boot application.
 */
public class ResponseUtil {
    private static final Gson gson = new Gson(); // Gson instance for JSON conversion

    /**
     * Creates a new JSON response object with a timestamp.
     *
     * @return A JsonObject with a timestamp field.
     */
    public static JsonObject createNewResponse() {
        JsonObject response = new JsonObject();
        response.addProperty("timestamp", Instant.now().toString());
        return response;
    }

    /**
     * Adds a key-value pair to the JSON response.
     *
     * @param response The existing JSON response object.
     * @param key      The key to add.
     * @param value    The value to add.
     * @return Updated JsonObject with the new key-value pair.
     */
    public static JsonObject addToResponse(JsonObject response, String key, String value) {
        response.addProperty(key, value);
        return response;
    }

    /**
     * Adds a nested JSON object under a specific key in the response.
     *
     * @param response The existing JSON response object.
     * @param key      The key under which the JSON object should be added.
     * @param value    The JSON object to add.
     * @return Updated JsonObject with the nested object.
     */
    public static JsonObject addToResponse(JsonObject response, String key, JsonObject value) {
        response.add(key, value);
        return response;
    }

    /**
     * Merges another JSON object into the response.
     *
     * @param response The existing JSON response object.
     * @param values   The JSON object to merge.
     * @return Updated JsonObject with merged values.
     */
    public static JsonObject addToResponse(JsonObject response, JsonObject values) {
        values.entrySet().forEach(entry -> response.add(entry.getKey(), entry.getValue()));
        return response;
    }

    /**
     * Returns a ResponseEntity with an HTTP 200 OK status and the given JSON response.
     *
     * @param response The JSON object to return.
     * @return ResponseEntity containing the JSON object.
     */
    public static ResponseEntity<String> returnOK(JsonObject response) {
        return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(response));
    }

    /**
     * Returns a ResponseEntity with an HTTP 400 Bad Request status and an error message.
     *
     * @param errorMessage The error message to include.
     * @return ResponseEntity containing an error response.
     */
    public static ResponseEntity<String> returnNotOK(String errorMessage) {
        JsonObject errorResponse = new JsonObject();
        errorResponse.addProperty("status", "error");
        errorResponse.addProperty("message", errorMessage);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(gson.toJson(errorResponse));
    }
}

