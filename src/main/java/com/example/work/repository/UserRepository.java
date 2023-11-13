package com.example.work.repository;

import com.example.work.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/*************************************
 * Users model  repository layer class
 **************************************/
public interface UserRepository extends JpaRepository<Users,Integer> {
    Optional<Users> findByEmail(String email);

    Users findByUserPassword(String password);
}
