package com.example.sprint4.repository;

import com.example.sprint4.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String username);
    Boolean existsByUsername(String username);}
