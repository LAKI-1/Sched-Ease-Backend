package com.sched_ease.backend.controllers;

import com.google.gson.JsonObject;
import com.sched_ease.backend.database.entities.ExternalAdministrator;
import com.sched_ease.backend.database.entities.SDGPLecturer;
import com.sched_ease.backend.database.entities.SDGPStudent;
import com.sched_ease.backend.database.services.*;
import com.sched_ease.backend.dto.UserResponse;
import com.sched_ease.backend.utilities.JwtUtil;
import com.sched_ease.backend.utilities.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/login")
@CrossOrigin(origins = "http://localhost:5173") // Allow requests from frontend
public class LoginController {

    private final JwtUtil jwtUtil;
    private final SDGPStudentService sdgpStudentService;
    private final SDGPLecturerService sdgpLecturerService;
    private final SDGPAdminService sdgpAdminService;
    private final ExternalAdministratorService exAdminService;

    @Autowired
    public LoginController(JwtUtil jwtUtil, SDGPStudentService sdgpStudentService,
                           SDGPLecturerService sdgpLecturerService, SDGPAdminService sdgpAdminService,
                           ExternalAdministratorService externalAdministratorService) {
        this.jwtUtil = jwtUtil;
        this.sdgpStudentService = sdgpStudentService;
        this.sdgpLecturerService = sdgpLecturerService;
        this.sdgpAdminService = sdgpAdminService;
        this.exAdminService = externalAdministratorService;
    }

    private JsonObject buildResponse(String token) {

        System.out.println("Token: "+token);

        JsonObject response = ResponseUtil.createNewResponse();
        response = ResponseUtil.addToResponse(response, "Token_status", String.valueOf(JwtUtil.validateToken(token)));
//        response = ResponseUtil.addToResponse(response, "Metadata", JwtUtil.extractMetaData(token));
        return response;
    }

    @PostMapping("/student-login")
    public ResponseEntity<String> studentLogin(@RequestHeader(value = "Authorization", required = true) String token) {
        try {
            JsonObject response = buildResponse(token);
            SDGPStudent student = sdgpStudentService.getStudentByToken(token);
            response = ResponseUtil.addToResponse(response, "SDGP_Student", student.toJson());

            UserResponse userResponse = new UserResponse(student);
            userResponse.setAvatar(JwtUtil.extractAvatar(token));
            response = ResponseUtil.addToResponse(response, "user", userResponse.toJson());
            System.out.println("OK: " + response);
            return ResponseUtil.returnOK(response);
//            return response;
        } catch (Exception e) {
            System.out.println("BAD: "+e.getMessage());
            return ResponseUtil.returnNotOK( "Failed to authenticate student: " + e.getMessage());
        }
    }

    @PostMapping("/lecturer-login")
    public ResponseEntity<String> lecturerLogin(@RequestHeader(value = "Authorization", required = true) String token) {
        try {
            JsonObject response = buildResponse(token);
            SDGPLecturer lecturerByToken = sdgpLecturerService.getLecturerByToken(token);
            response = ResponseUtil.addToResponse(response, "SDGP_Lecturer", lecturerByToken.toJson());

            UserResponse userResponse = new UserResponse(lecturerByToken);
            userResponse.setAvatar(JwtUtil.extractAvatar(token));
            response = ResponseUtil.addToResponse(response, "user", userResponse.toJson());
            System.out.println("OK: " + response);
            return ResponseUtil.returnOK(response);
        } catch (Exception e) {
            System.out.println("BAD: "+e.getMessage());
            return ResponseUtil.returnNotOK( "Failed to authenticate Lecturer: " + e.getMessage());
        }
    }

    @PostMapping("/sdgp_admin-login")
    public ResponseEntity<String> adminLogin(@RequestHeader(value = "Authorization", required = true) String token) {
        try {
            JsonObject response = buildResponse(token);
            SDGPLecturer sdgpAdminByToken = sdgpLecturerService.getLecturerByToken(token);
            response = ResponseUtil.addToResponse(response, "SDGP_Admin_Lecturer", sdgpAdminByToken.toJson());

            UserResponse userResponse = new UserResponse(sdgpAdminByToken);
            userResponse.setAvatar(JwtUtil.extractAvatar(token));
            response = ResponseUtil.addToResponse(response, "user", userResponse.toJson());
            System.out.println("OK: " + response);
//            JsonObject response = buildResponse(token);
//            SDGPLecturer adminByToken = sdgpAdminService.getAdminByToken(token);
//            response = ResponseUtil.addToResponse(response, "SDGP_Administrator", adminByToken.toString());
            return ResponseUtil.returnOK(response);
        } catch (Exception e) {
            return ResponseUtil.returnNotOK( "Failed to authenticate SDGP Admin: " + e.getMessage());
        }
    }

    @PostMapping("/admin-login")
    public ResponseEntity<String> exAdminLogin(@RequestHeader(value = "Authorization", required = true) String token) {
        try {
            JsonObject response = buildResponse(token);
            ExternalAdministrator exAdminByEmail = exAdminService.getAdminByToken(token);
            response = ResponseUtil.addToResponse(response, "External_Administrator", exAdminByEmail.toJson());

            UserResponse userResponse = new UserResponse(exAdminByEmail);
            userResponse.setAvatar(JwtUtil.extractAvatar(token));
            response = ResponseUtil.addToResponse(response, "user", userResponse.toJson());

            return ResponseUtil.returnOK(response);
        } catch (Exception e) {
            return ResponseUtil.returnNotOK( "Failed to authenticate External Admin: " + e.getMessage());
        }
    }

    @PostMapping("/hello")
    public ResponseEntity<Map<String, String>> hello(@RequestHeader(value = "Authorization", required = true) String token) {
        try {
//            JsonObject response = buildResponse(token);
////            response = JsonResponseUtil.addToResponse(response, "External_Administrator", exAdminService.getExAdminByEmail(token).toString());
//            response = JsonResponseUtil.addToResponse(response, "reply", "hi");
//            return JsonResponseUtil.returnOK(response);
            return ResponseEntity.ok(Map.of(
                    "message", "Hello, World!",
                    "Authorization", token
            ));
        } catch (Exception e) {
//            return JsonResponseUtil.createErrorResponse(500, "Failed to authenticate user: " + e.getMessage());
            return ResponseEntity.ok(Map.of(
                    "error", "bad request"
            ));
        }
    }
}
