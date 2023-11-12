package com.example.work.auth;

import com.example.work.models.Card;
import com.example.work.models.Human;
import com.example.work.models.Role;
import com.example.work.models.Users;
import com.example.work.repository.LoginHumanRepository;
import com.example.work.security.JWTService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class AuthenticationServiceTest {
    @InjectMocks
    private AuthenticationService auth;
    @Mock
    private  LoginHumanRepository repository;

    @Mock
    private  PasswordEncoder passwordEncoder;

    @Mock
    private  LoginHumanRepository loginHumanRepository;

    @Mock
    private  JWTService jwtService;

    @Mock
    private  AuthenticationManager authenticationManager;
    @Test
    void register() throws Exception {
        // Given
        RegisterRequest request = new RegisterRequest();
        request.setFirstname("John");
        request.setLastname("Doe");
        request.setEmail("john.doe@example.com");
        request.setPassword("password");

        Human savedUser = Human.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password("encodedPassword")
                .role(Role.USER)
                .build();
        System.out.println(savedUser.getPassword());
        when(passwordEncoder.encode(request.getPassword())).thenReturn("encodedPassword");
        when(repository.save(any(Human.class))).thenReturn(savedUser);
        when(jwtService.generateToken(any(Human.class))).thenReturn("mockedJwtToken");

        // When
        AuthenticationResponse response=auth.register(request);

        // Then
        verify(passwordEncoder).encode(request.getPassword());
        verify(repository).save(any(Human.class));
        verify(jwtService).generateToken(savedUser);

        // Add assertions for the response
        // For example, you can assert that the generated token is not null or empty
        assertNotNull(response.getToken());
    }


}