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
        try {
            if (repo.findByEmail(email).isPresent()) {
                if (repo.findByEmail(email).get().isSDGPAdminFlag()) {
                    System.out.println("SDGP Lecturer found, SDGP Lecturer is Admin");
                    return repo.findByEmail(email).get();
                } else {
                    System.out.println("SDGP Lecturer found, SDGP Lecturer NOT is Admin");
                    return null;
                }
            } else {
                System.out.println("Lecturer not found, new Sdgp Lecturer cannot be created");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public SDGPLecturer getAdminByToken(String token){
        String email = JwtUtil.extractEmail(token);
        System.out.println(email);
        return getAdminByEmail(email);
//        return null;
    }
}
