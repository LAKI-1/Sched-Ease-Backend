package com.sched_ease.backend.database.services;

import com.sched_ease.backend.database.entities.Lecturer;
import com.sched_ease.backend.database.entities.SDGPLecturer;
import com.sched_ease.backend.database.entities.SDGPStudent;
import com.sched_ease.backend.database.entities.Student;
import com.sched_ease.backend.database.repositories.LecturerRepository;
import com.sched_ease.backend.database.repositories.SDGPLecturerRepository;
import com.sched_ease.backend.database.repositories.SDGPStudentRepository;
import com.sched_ease.backend.database.repositories.StudentRepository;
import com.sched_ease.backend.utilities.JwtUtil;
import org.springframework.stereotype.Service;

@Service
public class SDGPLecturerService {

    private final SDGPLecturerRepository repo;
    private final LecturerRepository lecturerRepo;
//    private final JwtUtil jwtUtil;

    public SDGPLecturerService(SDGPLecturerRepository repo, LecturerRepository lecturerRepo) {
        this.repo = repo;
        this.lecturerRepo = lecturerRepo;
//        this.jwtUtil = jwtUtil;
    }

    public SDGPLecturer getLecturerByEmail(String email){
        Lecturer lecturer = lecturerRepo.findByEmail(email);
        if (lecturer == null){
            return null;
        }
        else{
            SDGPLecturer sdgpLecturer = new SDGPLecturer(lecturer);
            repo.save(sdgpLecturer);
            return sdgpLecturer;
        }
    }

    public SDGPLecturer getLecturerByToken(String token){
        String email = JwtUtil.extractEmail(token);
        return getLecturerByEmail(email);
    }
}
