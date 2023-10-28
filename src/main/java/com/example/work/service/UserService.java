package com.example.work.service;
import com.example.work.dto.UserDto;
import com.example.work.models.Card;
import com.example.work.models.Users;

import java.util.Optional;

public interface UserService {
    Users userSave(UserDto user);
    Users findByEmail(String email);
}