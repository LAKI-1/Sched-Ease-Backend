package com.sched_ease.backend.database.services;

import com.sched_ease.backend.database.entities.Lecturer;
import com.sched_ease.backend.database.entities.SDGPLecturer;
import com.sched_ease.backend.database.repositories.LecturerRepository;
import com.sched_ease.backend.database.repositories.SDGPLecturerRepository;
import com.sched_ease.backend.utilities.JwtUtil;
import org.springframework.stereotype.Service;

@Service
public class SDGPAdminService {

    private final SDGPLecturerRepository repo;
    private final LecturerRepository lecturerRepo;

    public SDGPAdminService(SDGPLecturerRepository repo, LecturerRepository lecturerRepo) {
        this.repo = repo;
        this.lecturerRepo = lecturerRepo;
    }

    public SDGPLecturer getAdminByEmail(String email){
        SDGPLecturer admin = repo.findByEmail(email);
        if (admin == null || !admin.isSDGPAdminFlag()){
            return null;
        }
        return admin;
    }

    public SDGPLecturer getAdminByToken(String token){
        String email = JwtUtil.extractEmail(token);
        return getAdminByEmail(email);
    }
}
