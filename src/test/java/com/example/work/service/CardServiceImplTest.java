package com.example.work.service;

import static org.junit.jupiter.api.Assertions.*;
import com.example.work.models.Card;
import com.example.work.models.Users;
import com.example.work.repository.CardRepository;
import com.example.work.repository.UserRepository;
import com.example.work.service.impl.CardServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CardServiceImplTest {

    @Mock
    private CardRepository cardRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CardServiceImpl cardService;

    @Test
    void testSaveCardValidCard() {

        // Arrange
        Card cardToSave = Card.builder()
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
        List<Card> cardList = new ArrayList<>();
        Users user = Users.builder()
                .id(1)
                .name("Jane")
                .surname("Smith")
                .email("jane@example.com")
                .cards(cardList) // Create a List of Cards or set it to null
                .userPassword("securePassword")
                .build();
        when(userRepository.findByEmail(cardToSave.getEmail())).thenReturn(Optional.of(user));
        when(cardRepository.findByPincode(cardToSave.getPincode())).thenReturn(null);
        when(cardRepository.findByCardPassword(cardToSave.getCardPassword())).thenReturn(null);
        when(cardRepository.save(any(Card.class))).thenReturn(cardToSave);

        // Act
        Card savedCard = cardService.saveCard(cardToSave);

        // Assert
        assertNotNull(savedCard);
        assertEquals(cardToSave, savedCard);
        // Add more assertions if needed based on your specific implementation
    }


    @Test
    void testDoPaymentSufficientBalance() {
        // Arrange
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
        when(cardRepository.findByCardPassword(card.getCardPassword())).thenReturn(card);

        // Act
          cardService.doPayment(card, 50L);
        // Add more assertions if needed based on your specific implementation
    }

    @Test
    void testDoPaymentInsufficientBalance() {
        // Arrange
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
        when(cardRepository.findByCardPassword(card.getCardPassword())).thenReturn(card);

        // Act
        cardService.doPayment((card), 150L);

        // Assert

        // Add more assertions if needed based on your specific implementation
    }

    // Add more test cases for different scenarios such as card not found, etc.
}