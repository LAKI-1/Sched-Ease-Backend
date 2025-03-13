package com.sched_ease.backend.login;


import com.sched_ease.backend.database.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/login")
@CrossOrigin(origins = "http://localhost:5173") // Allow requests from frontend
public class LoginController {

    @Autowired
    private final JwtUtil jwtUtil;
    private final SDGPStudentService sdgpStudentService;
    private final SDGPLecturerService sdgpLecturerService;
    private final SDGPAdminService sdgpAdminService;
    private final ExternalAdministratorService exAdminService;

//    private UserService userService;

    public LoginController(JwtUtil jwtUtil, SDGPStudentService sdgpStudentService, SDGPLecturerService sdgpLecturerService, SDGPAdminService sdgpAdminService, ExternalAdministratorService externalAdministratorService) {
        this.jwtUtil = jwtUtil;
        this.sdgpStudentService = sdgpStudentService;
        this.sdgpLecturerService = sdgpLecturerService;
        this.sdgpAdminService = sdgpAdminService;
        this.exAdminService = externalAdministratorService;
//        this.userService = userService;
    }

    private Map<String, Object> returnObj(String token){

        Map<String, Object> response = new HashMap<>();

        response.put("Token_status", JwtUtil.validateToken(token));
        response.put("Metadata", JwtUtil.extractMetaData(token));
        System.out.println(response);
        return response;
    }

    @PostMapping("/student-login")
    public Map<String, Object> studentLogin(
            @RequestHeader(value = "Authorization", required = true) String token) {
        System.out.println("token: " + token);

        Map<String, Object> response = returnObj(token);
        response.put("SDGP_Student", sdgpStudentService.getStudentByToken(token));

        System.out.println(response);
        return response;
    }

    @PostMapping("/lecturer-login")
    public Map<String, Object> lecturerLogin(
            @RequestHeader(value = "Authorization", required = true) String token) {

        Map<String, Object> response = returnObj(token);
        response.put("SDGP_Lecturer", sdgpLecturerService.getLecturerByToken(token));
        return response;
    }

    @PostMapping("/sdgp_admin-login")
    public Map<String, Object> adminLogin(
            @RequestHeader(value = "Authorization", required = true) String token) {

        Map<String, Object> response = returnObj(token);
        response.put("SDGP_Administrator", sdgpAdminService.getAdminByToken(token));
        return response;
    }

    @PostMapping("/admin-login")
    public Map<String, Object> exAdminLogin(
            @RequestHeader(value = "Authorization", required = true) String token) {

        Map<String, Object> response = returnObj(token);
        response.put("Exteranl_Administrator", exAdminService.getExAdminByEmail(token));
        return response;
    }
}
