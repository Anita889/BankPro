package com.example.work.auth;

import com.example.work.models.Card;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
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
    void register() {
    }

    @Test
    void authenticate() {
        //Arrange
        AuthenticationRequest request = new AuthenticationRequest("test@example.com", "password");

        List<Card> cardList = new ArrayList<>();
        Users mockUser = Users.builder()
                .id(1)
                .name("Jane")
                .surname("Smith")
                .email("jane@example.com")
                .cards(cardList)
                .userPassword("securePassword")
                .build();
        String mockToken = "mockedJWTToken";

        when(authenticationManager.authenticate(any()));
        when(loginHumanRepository.findByEmail(eq(request.getEmail()))).thenReturn(Optional.of(mockUser));
        when(jwtService.generateToken((UserDetails) eq(mockUser)).

        AuthenticationResponse response = auth.authenticate(request);
    }
}