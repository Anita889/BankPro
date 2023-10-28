package com.example.work.service.impl;

import com.example.work.dto.UserDto;
import com.example.work.mapping.UserMap;
import com.example.work.models.Role;
import com.example.work.models.Users;
import com.example.work.repository.UserRepository;
import com.example.work.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Users userSave(UserDto userDto) {
        if((Character.isUpperCase(userDto.getName().charAt(0)))
            &&(Character.isUpperCase(userDto.getSurname().charAt(0)))
            &&userDto.getEmail().contains("@gmail.com")
            &&userDto.getPassword().length()==8
            &&userRepository.findByPassword(userDto.getPassword())!=null) {
            Users user = UserMap.dto_model_User(userDto);
            return userRepository.save(user);
        }
        else return null;
    }
    @Override
    public Users findByEmail(String email) {
        return   userRepository.findByEmail(email);
    }

}
