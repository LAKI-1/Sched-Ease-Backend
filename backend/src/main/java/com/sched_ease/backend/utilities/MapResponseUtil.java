package com.sched_ease.backend.utilities;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**************************************************/
//
//          THIS CLASS IS DEPRECATED
//          DO NOT USE TO MAKE FRONT END CALLS
//
/**************************************************/

public class MapResponseUtil {

    /**
     * Creates a new response map with only a timestamp.
     *
     * @return Map<String, String> - A blank response map with a timestamp.
     */
    public static Map<String, String> createNewResponse() {
        Map<String, String> response = new HashMap<>();
        response.put("timestamp", Instant.now().toString());
        return response;
    }

    /**
     * Adds a key-value pair to the existing response map.
     *
     * @param response The existing response map.
     * @param key      The key to be added.
     * @param value    The value to be added.
     * @return Updated Map<String, String> with the new key-value pair.
     */
    public static Map<String, String> addToResponse(Map<String, String> response, String key, String value) {
        response.put(key, value);
        return response;
    }

    /**
     * Adds all key-value pairs from another map to the existing response map.
     *
     * @param response The existing response map.
     * @param data     The map containing key-value pairs to be added.
     * @return Updated Map<String, String> with all new key-value pairs.
     */
    public static Map<String, String> addToResponse(Map<String, String> response, Map<String, String> data) {
        response.putAll(data);
        return response;
    }

    /**
     * Adds a nested Map<String, String> as a value to the response map under a specific key.
     * Instead of converting it to JSON, it flattens the nested map into the main response map.
     *
     * @param response The existing response map.
     * @param key      The key under which the nested map should be added.
     * @param values   The nested map to be added.
     * @return Updated Map<String, String> with the nested key-value pairs.
     */
    public static Map<String, String> addToResponse(Map<String, String> response, String key, Map<String, String> values) {
        for (Map.Entry<String, String> entry : values.entrySet()) {
            response.put(key + "." + entry.getKey(), entry.getValue()); // Flatten nested values
        }
        return response;
    }


    /**
     * Returns a ResponseEntity with an error message and HTTP status BAD_REQUEST (400).
     *
     * @param errorMessage The error message to be included in the response.
     * @return ResponseEntity<Map<String, String>> - A response entity with error details.
     */
    public static ResponseEntity<Map<String, String>> returnNotOk(String errorMessage) {
        Map<String, String> errorResponse = createNewResponse();
        errorResponse.put("status", "error");
        errorResponse.put("message", errorMessage);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Returns a ResponseEntity with the given response map and HTTP status OK (200).
     *
     * @param response The response map containing the data.
     * @return ResponseEntity<Map<String, String>> - A successful response entity.
     */
    public static ResponseEntity<Map<String, String>> returnOK(Map<String, String> response) {
        response.put("status", "success");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

