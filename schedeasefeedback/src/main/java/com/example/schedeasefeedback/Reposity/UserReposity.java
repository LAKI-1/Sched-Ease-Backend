package com.example.schedeasefeedback.Reposity;



import com.example.schedeasefeedback.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface UserReposity extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}

