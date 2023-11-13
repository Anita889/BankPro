package com.example.work.repository;

import com.example.work.models.Human;
import org.springframework.data.jpa.repository.JpaRepository;


/*************************************
 * Human model repository layer class
 **************************************/
public interface LoginHumanRepository extends JpaRepository<Human, Integer> {
    Human findByEmail(String username);
}
