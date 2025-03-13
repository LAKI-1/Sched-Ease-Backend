package com.sched_ease.backend.database.services;

import com.sched_ease.backend.database.entities.SDGPStudent;
import com.sched_ease.backend.database.entities.Student;
import com.sched_ease.backend.database.repositories.SDGPStudentRepository;
import com.sched_ease.backend.database.repositories.StudentRepository;
import com.sched_ease.backend.login.JwtUtil;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SDGPStudentService {

    private final SDGPStudentRepository repo;
    private final StudentRepository studentRepo;
    private final JwtUtil jwtUtil;

    public SDGPStudentService(SDGPStudentRepository repo, StudentRepository studentRepo, JwtUtil jwtUtil) {
        this.repo = repo;
        this.studentRepo = studentRepo;
        this.jwtUtil = jwtUtil;
    }

    public SDGPStudent getStudentByEmail(String email) {
        // Check if an SDGPStudent already exists
        return repo.findByEmail(email)
                .orElseGet(() -> studentRepo.findByEmail(email)
                        .map(student -> {
                            System.out.println("Student: " + student.getName() + " found. Creating new SDGP Student");
                            SDGPStudent sdgpStudent = new SDGPStudent(student);
                            repo.save(sdgpStudent);
                            System.out.println(sdgpStudent.getName() + " : made SDGP student");
                            return sdgpStudent;
                        })
                        .orElseGet(() -> {
                            System.out.println("No student or SDGP student found with this email.");
                            return null; // Consider throwing an exception instead of returning null
                        }));
    }

    public SDGPStudent getStudentByToken(String token){
        String email = JwtUtil.extractEmail(token);
        System.out.println(email);
        return getStudentByEmail(email);
    }

}
