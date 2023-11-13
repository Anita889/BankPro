package com.example.work.repository;

import com.example.work.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/*************************************
 * Card model  repository layer class
 **************************************/
public interface CardRepository extends JpaRepository<Card,Integer> {
    Card findByPincode(Long pincode);
    Card findByCardPassword(String password);
    Optional<Card> findById(Integer id);
}
