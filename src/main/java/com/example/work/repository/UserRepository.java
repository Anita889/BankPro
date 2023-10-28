package com.example.work.repository;

import com.example.work.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users,Integer> {
    Users findByEmail(String email);

    Users findByPassword(String password);
}
