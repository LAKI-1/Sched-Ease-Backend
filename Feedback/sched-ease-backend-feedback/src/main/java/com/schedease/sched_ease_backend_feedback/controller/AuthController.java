package com.schedease.sched_ease_backend_feedback.controller;


import com.schedease.sched_ease_backend_feedback.dto.AuthenticationRequest;
import com.schedease.sched_ease_backend_feedback.dto.AuthenticationResponse;
import com.schedease.service.CustomUserDetailsService;
import com.schedease.sched_ease_backend_feedback.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest) {
        String username = authenticationRequest.getUsername();
        String password = authenticationRequest.getPassword();

        // Authenticate user using username and password
        if (userDetailsService.authenticate(username, password)) {
            // Generate JWT token if credentials are correct
            String token = jwtTokenUtil.generateToken(username);
            return ResponseEntity.ok(new AuthenticationResponse(token));
        }

        return ResponseEntity.status(401).body(null); // Unauthorized response
    }
}

