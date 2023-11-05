package com.example.work.service;
import com.example.work.dto.UserDto;
import com.example.work.models.Users;


public interface UserService {
    Users userSave(UserDto user);
    Users findByEmail(String email);
}