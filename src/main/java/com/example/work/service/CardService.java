package com.example.work.service;

import com.example.work.dto.CardDto;
import com.example.work.models.Card;

public interface CardService  {
    Card findByPincode(Long pincode);
    Card saveCard(CardDto cardDto);
}
