package com.example.work.repository;

import com.example.work.models.Human;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginHumanRepository extends JpaRepository<Human, Integer> {
    Human findByEmail(String username);
}
