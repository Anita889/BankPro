package com.example.work.mapping;

import com.example.work.dto.CardDto;
import com.example.work.models.Card;

public class CardMap {
    public static Card dto_model_Card(CardDto cardDto)
    {
        Card card=new Card();
        card.setCardId(cardDto.getId());
        card.setSurname(cardDto.getSurname());
        card.setName(cardDto.getName());
        card.setEmail(cardDto.getEmail());
        card.setPassword(cardDto.getPassword());

        card.setAccount(cardDto.getAccount());
        card.setAccount(cardDto.getAccount());
        card.setPincode(cardDto.getPincode());
        card.setStart(cardDto.getStart());
        card.setFinish(cardDto.getFinish());
        card.setUser(cardDto.getUser());
        return card;
    }
}
