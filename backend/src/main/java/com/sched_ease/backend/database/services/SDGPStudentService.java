package com.sched_ease.backend.database.services;

import com.sched_ease.backend.database.entities.SDGPStudent;
import com.sched_ease.backend.database.entities.Student;
import com.sched_ease.backend.database.repositories.SDGPStudentRepository;
import com.sched_ease.backend.database.repositories.StudentRepository;
import com.sched_ease.backend.utilities.JwtUtil;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;


@Service
public class SDGPStudentService {

    private final SDGPStudentRepository repo;
    private final StudentRepository studentRepo;
    private final JwtUtil jwtUtil;

    @PersistenceContext
    private EntityManager entityManager;

    public SDGPStudentService(SDGPStudentRepository repo, StudentRepository studentRepo, JwtUtil jwtUtil) {
        this.repo = repo;
        this.studentRepo = studentRepo;
        this.jwtUtil = jwtUtil;
    }

    @Transactional
    public SDGPStudent getSDGPStudentByEmail(String email) {

        try {
            if (repo.findByEmail(email).isPresent()) {
                System.out.println("sdgpstudent found.");
                return repo.findByEmail(email).get();
            } else if (studentRepo.findByEmail(email).isPresent()) {
                System.out.println("sdgp student not found, student found: " + studentRepo.findByEmail(email).get() + "\n creating sdgp student from student");

                Student student = studentRepo.findByEmail(email).get();

//                if(repo.findById(student.getId()).isPresent()){
//                    System.out.println("SDGP student with student id exists");
//                }

                SDGPStudent newsdgpStudent = new SDGPStudent(student);
                System.out.println("new sdgp student created: " + newsdgpStudent.toJson().toString());

                entityManager.detach(student);
                newsdgpStudent = repo.save(newsdgpStudent);

//                newsdgpStudent = entityManager.merge(newsdgpStudent);

                System.out.println("saved sdgpStudent");
                return newsdgpStudent;
            } else {
                System.out.println("Student not found, new sdgpstudent cannot be created");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: "+e.getMessage());
            return null;
        }
    }

    public SDGPStudent getStudentByToken(String token){
        String email = JwtUtil.extractEmail(token);
        System.out.println(email);
        return getSDGPStudentByEmail(email);
    }
    public List<SDGPStudent> getAllInRepo() {
        return repo.findAll();
    }

    public Optional<Student> getStudentByEmail(String email){
        return studentRepo.findByEmail(email);
    }
}
