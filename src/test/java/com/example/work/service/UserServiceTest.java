package com.example.work.service;



import com.example.work.dto.UserDto;
import com.example.work.models.Card;
import com.example.work.models.Users;
import com.example.work.repository.UserRepository;
import com.example.work.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;


    @Test
    void testUserSaveInvalidUser() {
        // Arrange
        // Create a List of Cards or set it to null
        List<Card> cardList=new ArrayList<>();
        UserDto invalidUserDto = UserDto.builder()
                .id(1)
                .name("John")
                .surname("Doe")
                .email("john@example.com")
                .cards(cardList)
                .userPassword("password123")
                .build();
        // Add more mock behaviors based on your invalid conditions

        // Act
        Users savedUser = userService.userSave(invalidUserDto);

        // Assert
        assertNull(savedUser);
        // Add more assertions based on your specific implementation
    }

    @Test
    void testFindByEmailUserExists() {
        // Arrange
        String userEmail = "existing@gmail.com";
        when(userRepository.findByEmail(userEmail))
                .thenReturn(Optional.of(new Users())); // You can customize the return value if needed

        // Act
        Users foundUser = userService.findByEmail(userEmail);

        // Assert
        assertNotNull(foundUser);
        // Add more assertions if needed based on your specific implementation
    }

    @Test
    void testFindByEmailUserDoesNotExist() {
        // Arrange
        String userEmail = "nonexistent@gmail.com";
        when(userRepository.findByEmail(userEmail)).thenReturn(Optional.empty());

        // Act
        Users foundUser = userService.findByEmail(userEmail);

        // Assert
        assertNull(foundUser);
        // Add more assertions if needed based on your specific implementation
    }
}
