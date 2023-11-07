package com.example.work.mapping;

import com.example.work.dto.UserDto;
import com.example.work.models.Card;
import com.example.work.models.Users;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class UserMapTest {

    @Test
    public void testDtoModelUser() {
        // Create a UserDto for testing

        List<Card> cardList=new ArrayList<>();
        UserDto userDto = UserDto.builder()
                .id(1)
                .name("John")
                .surname("Doe")
                .email("john@example.com")
                .cards(cardList) // Create a List of Cards or set it to null
                .userPassword("password123")
                .build();

        // Call the dto_model_User method to convert UserDto to Users
        Users user = UserMap.dto_model_User(userDto);

        // Verify if the conversion is correct
        assertEquals(userDto.getId(), user.getId());
        assertEquals(userDto.getName(), user.getName());
        assertEquals(userDto.getSurname(), user.getSurname());
        assertEquals(userDto.getEmail(), user.getEmail());
        // You may need to adjust the comparison for the cards field based on your actual implementation.
        assertEquals(userDto.getUserPassword(), user.getUserPassword());
    }

    @Test
    public void testModelDtoUser() {
        // Create a Users for testing
        List<Card> cardList = new ArrayList<>();
        Users user = Users.builder()
                .id(1)
                .name("Jane")
                .surname("Smith")
                .email("jane@example.com")
                .cards(cardList) // Create a List of Cards or set it to null
                .userPassword("securePassword")
                .build();

        // Call the model_Dto_User method to convert Users to UserDto
        UserDto userDto = UserMap.model_Dto_User(user);
        // Verify if the conversion is correct
        assertEquals(user.getId(), userDto.getId());
        assertEquals(user.getName(), userDto.getName());
        assertEquals(user.getSurname(), userDto.getSurname());
        assertEquals(user.getEmail(), userDto.getEmail());
        // You may need to adjust the comparison for the cards field based on your actual implementation.
        assertEquals(user.getUserPassword(), userDto.getUserPassword());
    }
}
