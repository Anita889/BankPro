package com.example.work.repository;

import com.example.work.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card,Integer> {
    Card findByPincode(Long pincode);
    Card findByCardPassword(String password);
}
