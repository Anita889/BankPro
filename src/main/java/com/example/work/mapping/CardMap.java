package com.example.work.mapping;

import com.example.work.dto.CardDto;
import com.example.work.models.Card;

public class CardMap {
    public static Card dto_model_Card(CardDto cardDto)
    {
        return Card.builder().
                id(cardDto.getId()).
                name(cardDto.getName()).
                surname(cardDto.getSurname()).
                pincode(cardDto.getPincode()).
                email(cardDto.getEmail()).
                user(cardDto.getUser()).
                finish(cardDto.getFinish()).
                start(cardDto.getStart()).
                account(cardDto.getAccount()).
                cardPassword(cardDto.getCardPassword()).build();
    }

    public static CardDto model_Dto_Card(Card card)
    {
        return  CardDto.builder().
                id(card.getId()).
                name(card.getName()).
                surname(card.getSurname()).
                pincode(card.getPincode()).
                email(card.getEmail()).
                finish(card.getFinish()).
                start(card.getStart()).
                account(card.getAccount()).
                cardPassword(card.getCardPassword()).build();
    }

}
