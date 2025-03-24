package com.schedease.sched_ease_backend_feedback.controller;



import com.schedease.entity.Student;
import com.schedease.entity.Lecturer;
import com.schedease.repository.StudentRepository;
import com.schedease.repository.LecturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private LecturerRepository lecturerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Try to find the user in the student or lecturer repository
        Student student = studentRepository.findByUsername(username);
        if (student != null) {
            return new User(student.getUsername(), student.getPassword(), student.getRoles());
        }

        Lecturer lecturer = lecturerRepository.findByUsername(username);
        if (lecturer != null) {
            return new User(lecturer.getUsername(), lecturer.getPassword(), lecturer.getRoles());
        }

        throw new UsernameNotFoundException("User not found with username: " + username);
    }

    // Method to authenticate user credentials (for login)
    public boolean authenticate(String username, String password) {
        try {
            UserDetails user = loadUserByUsername(username);
            return user.getPassword().equals(password);
        } catch (UsernameNotFoundException e) {
            return false;
        }
    }
}

