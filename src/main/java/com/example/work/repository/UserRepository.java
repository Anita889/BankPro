package com.example.work.repository;

import com.example.work.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<Users,Integer> {
    Optional<Users> findByEmail(String email);

    Users findByUserPassword(String password);
}
