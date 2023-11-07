package com.example.work.mapping;

import com.example.work.dto.UserDto;
import com.example.work.models.Role;
import com.example.work.models.Users;

import java.util.stream.Collectors;

public class UserMap {
    public static Users dto_model_User(UserDto userDto) {
        return Users.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .email(userDto.getEmail())
                .cards(userDto.getCards())
                .userPassword(userDto.getUserPassword())
                .build();
    }

    public static UserDto model_Dto_User(Users user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .cards(user.getCards())
                .surname(user.getSurname())
                .email(user.getEmail())
                .userPassword(user.getUserPassword())
                .build();
    }


}
