package com.sched_ease.backend.utilities;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.Map;

/**************************************************/
//
//          THIS CLASS IS DEPRECATED
//          DO NOT USE TO MAKE FRONT END CALLS
//
/**************************************************/


public class JsonResponseUtil {
    private static final Gson gson = new Gson();

    /**
     * Creates a new JSON response with only a timestamp.
     * @return JsonObject with a "timestamp" field.
     */
    public static JsonObject createNewJsonResponse() {
        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("timestamp", Instant.now().toString());
        return jsonResponse;
    }

    /**
     * Adds a key-value pair to an existing JsonObject.
     * @param jsonResponse The existing JsonObject.
     * @param key The key to add.
     * @param value The value to associate with the key.
     * @return Updated JsonObject with the new key-value pair.
     */
    public static JsonObject addToResponse(JsonObject jsonResponse, String key, String value) {
        jsonResponse.addProperty(key, value);
        return jsonResponse;
    }

    /**
     * Adds all key-value pairs from a Map to an existing JsonObject.
     * @param jsonResponse The existing JsonObject.
     * @param map The Map containing key-value pairs.
     * @return Updated JsonObject with all the new key-value pairs.
     */
    public static JsonObject addToResponse(JsonObject jsonResponse, Map<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            jsonResponse.addProperty(entry.getKey(), entry.getValue());
        }
        return jsonResponse;
    }

    /**
     * Creates a standardized error response JSON object.
     * @param errorCode The HTTP status code (e.g., 404, 500).
     * @param customError The custom error message.
     * @return JsonObject containing "status", "defaultErrorMessage", and "customError".
     */
    public static JsonObject createErrorResponse(int errorCode, String customError) {
        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("status", errorCode);
        jsonResponse.addProperty("defaultErrorMessage", HttpStatus.valueOf(errorCode).getReasonPhrase());
        jsonResponse.addProperty("customError", customError);
        return jsonResponse;
    }

    /**
     * Marks a JsonObject response as successful by adding a "status": 200 field.
     * @param jsonResponse The existing JsonObject.
     * @return Updated JsonObject with status 200.
     */
    public static JsonObject returnOK(JsonObject jsonResponse) {
        jsonResponse.addProperty("status", HttpStatus.OK.value());
        return jsonResponse;
    }
}
