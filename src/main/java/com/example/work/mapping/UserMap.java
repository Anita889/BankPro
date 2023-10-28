package com.example.work.mapping;

import com.example.work.dto.UserDto;
import com.example.work.models.Role;
import com.example.work.models.Users;

public class UserMap {
    public static Users dto_model_User(UserDto userDto){

        Users user=new Users();
        user.setUserId(userDto.getId());
        user.setSurname(userDto.getSurname());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setCards(userDto.getCards());
        Role role=Role.USER;
        user.setRole(role);
        return  user;
    }
}
