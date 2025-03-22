package com.sched_ease.backend.database.services;

import com.sched_ease.backend.database.entities.ExternalAdministrator;
import com.sched_ease.backend.database.entities.Lecturer;
import com.sched_ease.backend.database.entities.SDGPLecturer;
import com.sched_ease.backend.database.repositories.ExternalAdministratorRepository;
import com.sched_ease.backend.utilities.JwtUtil;
import org.springframework.stereotype.Service;

@Service
public class ExternalAdministratorService {
    
    private final ExternalAdministratorRepository repo;

    public ExternalAdministratorService(ExternalAdministratorRepository repo) {
        this.repo = repo;
    }

    public ExternalAdministrator getExAdminByEmail(String email){
//        ExternalAdministrator exAdmin = repo.findByEmail(email);
//        return exAdmin;
        try {
            System.out.println(repo.findAll());
            if (repo.findByEmail(email).isPresent()) {
                System.out.println("External Administrator found.");
                return repo.findByEmail(email).get();
            } else {
                System.out.println("External Administrator not found, new External Administrator will not be created");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: "+e.getMessage());
            return null;
        }
    }

    public ExternalAdministrator getAdminByToken(String token){
        String email = JwtUtil.extractEmail(token);
        System.out.println(email);
        return getExAdminByEmail(email);
    }
}
