package com.sched_ease.backend.utilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    static Dotenv dotenv = Dotenv.configure()
            .filename("secrets.env") // Explicitly load secrets.env
            .ignoreIfMissing()
            .load();

    private static final String JWT_KEY = dotenv.get("JWT_KEY"); // Use a secure key!


    private static final Key key = Keys.hmacShaKeyFor(JWT_KEY.getBytes());

    private static final Gson gson = new GsonBuilder().create();

    public JwtUtil(){}

    // 🔹 Generate a signing key from the secret
    private static Key getSigningKey() {
        return Keys.hmacShaKeyFor(JWT_KEY.getBytes(StandardCharsets.UTF_8));
    }


    public static Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // 🔹 Extract username (or email) from the token
    public static String extractUsername(String token) {
//        return extractClaim(token, Claims::getSubject);
        return extractClaim(token, claims -> {
            Object userMetadata = claims.get("user_metadata");
            if (userMetadata instanceof Map) {
                return ((Map<String, Object>) userMetadata).get("full_name").toString();
            }
            return null; // Return null if "user_metadata" is missing
        });
    }

    public static String extractEmail(String token) {
//        return extractClaim(token, Claims::getSubject);
        return extractClaim(token, claims -> {
            Object userMetadata = claims.get("user_metadata");
            if (userMetadata instanceof Map) {
                return ((Map<String, Object>) userMetadata).get("email").toString();
            }
            return null; // Return null if "user_metadata" is missing
        });
    }

    public static String extractAvatar(String token) {
//        return extractClaim(token, Claims::getSubject);
        return extractClaim(token, claims -> {
            Object userMetadata = claims.get("user_metadata");

            String avatar = ((Map<String, Object>) userMetadata).get("avatar_url").toString();
            if (avatar != null) {
                return avatar;
            }

            avatar = ((Map<String, Object>) userMetadata).get("picture").toString();
            if (avatar != null) {
                return avatar;
            }

            return null; // Return null if "users avatar" is missing
        });
    }

//    public static String extractMetaData(String token) {
////        extractClaim(token, claims -> {claims.get("user_metadata.name")});
//        return extractClaim(token, claims -> claims.get("user_metadata").toString());
//    }
    public static JsonObject extractMetaData(String token) {
        return extractClaim(token, claims -> {
            Object metadata = claims.get("user_metadata");
            if (metadata instanceof Map) {
                // Convert the Map to a JsonObject
                return gson.toJsonTree(metadata).getAsJsonObject();
            }
            return new JsonObject(); // Return an empty JsonObject if metadata is missing
        });
    }

    // 🔹 Extract User ID from the token
    public static String extractUserId(String token) {
        return extractClaim(token, claims -> claims.get("userId", String.class));
    }

    // 🔹 Extract roles from the token
    public static String extractUserRole(String token) {
        return extractClaim(token, claims -> claims.get("role", String.class));
    }

    // 🔹 Generic method to extract any claim
    public static <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // 🔹 Extract all claims
    private static Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public enum TokenStatus {
        VALID, INVALID, EXPIRED
    }

    public static TokenStatus validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return TokenStatus.VALID;
        } catch (ExpiredJwtException e) {
            return TokenStatus.EXPIRED;
        } catch (JwtException e) {
            return TokenStatus.INVALID;
        }
    }
}
