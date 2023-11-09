package com.example.work.service;

import com.example.work.dto.CardDto;
import com.example.work.models.Card;

import java.util.Optional;

public interface CardService  {
    Card findByPincode(Long pincode);

    Card saveCard(Card card);

    Optional<Card> findByCardId(Integer cardId);

    boolean doPayment(Optional<Card> card,Integer pay);

    Card findByCardPassword(String cardPassword);
}
