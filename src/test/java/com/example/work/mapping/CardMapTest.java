package com.example.work.mapping;

import com.example.work.dto.CardDto;
import com.example.work.models.Card;
import com.example.work.models.Users;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

public class CardMapTest {

    @Test
    public void testDtoModelCard() {
        // Create a CardDto for testing
        CardDto cardDto = CardDto.builder()
                .id(1)
                .name("John")
                .surname("Doe")
                .pincode(Long.valueOf("1234"))
                .email("john.doe@example.com")
                .user(new Users())
                .finish(Timestamp.valueOf("2023-11-07 15:30:45.123"))
                .start(Timestamp.valueOf("2022-11-07 16:50:45.113"))
                .account(Long.valueOf("12345678"))
                .cardPassword("password123")
                .build();

        // Call the dto_model_Card method to convert CardDto to Card
        Card card = CardMap.dto_model_Card(cardDto);

        // Verify if the conversion is correct
        assertEquals(cardDto.getId(), card.getId());
        assertEquals(cardDto.getName(), card.getName());
        assertEquals(cardDto.getSurname(), card.getSurname());
        assertEquals(cardDto.getPincode(), card.getPincode());
        assertEquals(cardDto.getEmail(), card.getEmail());
        assertEquals(cardDto.getUser(), card.getUser());
        assertEquals(cardDto.getFinish(), card.getFinish());
        assertEquals(cardDto.getStart(), card.getStart());
        assertEquals(cardDto.getAccount(), card.getAccount());
        assertEquals(cardDto.getCardPassword(), card.getCardPassword());
    }

    @Test
    public void testModelDtoCard() {
        // Create a Card for testing
        Card card = Card.builder()
                .id(1)
                .name("Jane")
                .surname("Smith")
                .pincode(Long.valueOf("5678"))
                .user(new Users())
                .email("jane.smith@example.com")
                .finish(Timestamp.valueOf("2023-11-05 00:39:23.772487"))
                .start(Timestamp.valueOf("2023-11-05 14:51:33.475223"))
                .account(Long.valueOf("98765432"))
                .cardPassword("securePassword")
                .build();

        // Call the model_Dto_Card method to convert Card to CardDto
        CardDto cardDto = CardMap.model_Dto_Card(card);

        // Verify if the conversion is correct
        assertEquals(card.getId(), cardDto.getId());
        assertEquals(card.getName(), cardDto.getName());
        assertEquals(card.getSurname(), cardDto.getSurname());
        assertEquals(card.getPincode(), cardDto.getPincode());
        assertEquals(card.getEmail(), cardDto.getEmail());
        assertEquals(card.getFinish(), cardDto.getFinish());
        assertEquals(card.getStart(), cardDto.getStart());
        assertEquals(card.getAccount(), cardDto.getAccount());
        assertEquals(card.getCardPassword(), cardDto.getCardPassword());
    }
}
