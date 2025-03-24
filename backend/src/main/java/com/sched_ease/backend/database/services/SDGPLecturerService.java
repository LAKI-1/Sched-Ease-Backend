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
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class SDGPLecturerService {

    private final SDGPLecturerRepository repo;
    private final LecturerRepository lecturerRepo;
//    private final JwtUtil jwtUtil;

    @PersistenceContext
    private EntityManager entityManager;

    public SDGPLecturerService(SDGPLecturerRepository repo, LecturerRepository lecturerRepo) {
        this.repo = repo;
        this.lecturerRepo = lecturerRepo;
//        this.jwtUtil = jwtUtil;
    }

    @Transactional
    public SDGPLecturer getLecturerByEmail(String email){
        try {
            if (repo.findByEmail(email).isPresent()) {
                System.out.println("sdgpLecturer found.");
                return repo.findByEmail(email).get();
            } else if (lecturerRepo.findByEmail(email).isPresent()) {
                System.out.println("sdgp lecturer not found, lecturer found: " + lecturerRepo.findByEmail(email).get() + "\n creating sdgp lecturer from lecturer");

                Lecturer lecturer = lecturerRepo.findByEmail(email).get();

                if(repo.findById(lecturer.getId()).isPresent()){
                    System.out.println("SDGP lecturer with lecturer id exists");
                }

                SDGPLecturer newSdgpLecturer = new SDGPLecturer(lecturer);
                System.out.println("new sdgp lecturer created: " + newSdgpLecturer.toJson().toString());

                entityManager.detach(lecturer);
                newSdgpLecturer= repo.save(newSdgpLecturer);

//                newSdgpLecturer = entityManager.merge(newSdgpLecturer);

                System.out.println("saved sdgpStudent");
                return newSdgpLecturer;
            } else {
                System.out.println("Lecturer not found, new Sdgp Lecturer cannot be created");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: "+e.getMessage());
            return null;
        }
    }

    public SDGPLecturer getLecturerByToken(String token){
        String email = JwtUtil.extractEmail(token);
        return getLecturerByEmail(email);
    }
}
