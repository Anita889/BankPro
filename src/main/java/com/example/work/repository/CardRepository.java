package com.example.work.repository;

import com.example.work.models.Card;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CardRepository extends CrudRepository<Card,Integer> {
    Card findByPincode(Long pincode);
    Card findByCardPassword(String password);
    Optional<Card> findById(Integer id);
}
