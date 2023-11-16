package com.example.work.auth;

import com.example.work.models.Human;
import com.example.work.models.Role;
import com.example.work.repository.LoginHumanRepository;
import com.example.work.security.JWTService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {
    @InjectMocks
    private AuthenticationService auth;
    @Mock
    private  LoginHumanRepository repository;

    @Mock
    private  PasswordEncoder passwordEncoder;

    @Mock
    private  JWTService jwtService;

    @Mock
    private  AuthenticationManager authenticationManager;


    @Test
    void register() {
        RegisterRequest request=new RegisterRequest();
        // Given
        request.setFirstname("John");
        request.setLastname("Doe");
        request.setEmail("john.doe@example.com");
        request.setPassword("password");
        request.setRole(Role.USER);

        Human savedUser = Human.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        // Mocking jwtService.generateToken to return a token
       jwtService.generateToken(savedUser);
        repository.save(savedUser);
        // When
        AuthenticationResponse response = auth.register(request);
        assertNotNull(response);
    }

}