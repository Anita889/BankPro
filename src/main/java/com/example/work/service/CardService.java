package com.example.work.service;

import com.example.work.models.Card;

import java.util.Optional;

public interface CardService  {
    Card findByPincode(Long pincode);

    Card saveCard(Card card);

    Optional<Card> findByCardId(Integer cardId);

    void doPayment(Card card,Long pay);

    Card findByCardPassword(String cardPassword);
}
