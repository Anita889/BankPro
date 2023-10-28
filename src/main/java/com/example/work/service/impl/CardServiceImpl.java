package com.example.work.service.impl;

import com.example.work.dto.CardDto;
import com.example.work.mapping.CardMap;
import com.example.work.mapping.UserMap;
import com.example.work.models.Card;
import com.example.work.models.Users;
import com.example.work.repository.CardRepository;
import com.example.work.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {
    private CardRepository cardRepository;

    @Autowired
    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }
    @Override
    public Card findByPincode(Long pincode) {
        return cardRepository.findByPincode(pincode);
    }

    @Override
    public Card saveCard(CardDto cardDto) {
        if(cardDto.getPassword().length()==8
        &&cardRepository.findByCardPassword(cardDto.getPassword())==null) {
            Card card = CardMap.dto_model_Card(cardDto);
            return cardRepository.save(card);
        }
        else return null;
    }
}