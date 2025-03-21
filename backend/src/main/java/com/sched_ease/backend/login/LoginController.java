package com.sched_ease.backend.login;

import com.google.gson.JsonObject;
import com.sched_ease.backend.database.services.*;
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

        JsonObject response = ResponseUtil.createNewResponse();
        response = ResponseUtil.addToResponse(response, "Token_status", String.valueOf(JwtUtil.validateToken(token)));
        response = ResponseUtil.addToResponse(response, "Metadata", JwtUtil.extractMetaData(token));
        return response;
    }

    @PostMapping("/student-login")
    public ResponseEntity<String> studentLogin(@RequestHeader(value = "Authorization", required = true) String token) {
        try {
            JsonObject response = buildResponse(token);
            response = ResponseUtil.addToResponse(response, "SDGP_Student", sdgpStudentService.getStudentByToken(token).toJson());
            System.out.println("OK: " + response);
            return ResponseUtil.returnOK(response);
//            return response;
        } catch (Exception e) {
            System.out.println("BAD");
            return ResponseUtil.returnNotOK( "Failed to authenticate student: " + e.getMessage());
        }
    }

    @PostMapping("/lecturer-login")
    public ResponseEntity<String> lecturerLogin(@RequestHeader(value = "Authorization", required = true) String token) {
        try {
            JsonObject response = buildResponse(token);
            response = ResponseUtil.addToResponse(response, "SDGP_Lecturer", sdgpLecturerService.getLecturerByToken(token).toString());
            return ResponseUtil.returnOK(response);
        } catch (Exception e) {
            return ResponseUtil.returnNotOK( "Failed to authenticate student: " + e.getMessage());
        }
    }

    @PostMapping("/sdgp_admin-login")
    public ResponseEntity<String> adminLogin(@RequestHeader(value = "Authorization", required = true) String token) {
        try {
            JsonObject response = buildResponse(token);
            response = ResponseUtil.addToResponse(response, "SDGP_Administrator", sdgpAdminService.getAdminByToken(token).toString());
            return ResponseUtil.returnOK(response);
        } catch (Exception e) {
            return ResponseUtil.returnNotOK( "Failed to authenticate student: " + e.getMessage());
        }
    }

    @PostMapping("/admin-login")
    public ResponseEntity<String> exAdminLogin(@RequestHeader(value = "Authorization", required = true) String token) {
        try {
            JsonObject response = buildResponse(token);
            response = ResponseUtil.addToResponse(response, "External_Administrator", exAdminService.getExAdminByEmail(token).toString());
            return ResponseUtil.returnOK(response);
        } catch (Exception e) {
            return ResponseUtil.returnNotOK( "Failed to authenticate student: " + e.getMessage());
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
