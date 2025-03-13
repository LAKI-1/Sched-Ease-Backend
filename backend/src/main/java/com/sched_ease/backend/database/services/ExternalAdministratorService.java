package com.sched_ease.backend.database.services;

import com.sched_ease.backend.database.entities.ExternalAdministrator;
import com.sched_ease.backend.database.entities.SDGPLecturer;
import com.sched_ease.backend.database.repositories.ExternalAdministratorRepository;
import com.sched_ease.backend.login.JwtUtil;
import org.springframework.stereotype.Service;

@Service
public class ExternalAdministratorService {
    
    private final ExternalAdministratorRepository repo;

    public ExternalAdministratorService(ExternalAdministratorRepository repo) {
        this.repo = repo;
    }

    public ExternalAdministrator getExAdminByEmail(String email){
        ExternalAdministrator exAdmin = repo.findByEmail(email);
        return exAdmin;
    }

    public ExternalAdministrator getAdminByToken(String token){
        String email = JwtUtil.extractEmail(token);
        return getExAdminByEmail(email);
    }
}
