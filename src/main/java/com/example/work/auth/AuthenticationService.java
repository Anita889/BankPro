package com.example.work.auth;


import com.example.work.models.Human;
import com.example.work.models.Role;
import com.example.work.repository.LoginHumanRepository;
import com.example.work.security.JWTService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final LoginHumanRepository repository;

    private final PasswordEncoder passwordEncoder;

    private final JWTService jwtService;

    private final AuthenticationManager authenticationManager;


    //Registration of new visitor
    public AuthenticationResponse register(RegisterRequest request) throws Exception {
        Human user = Human.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                // divide you logic for roles
                .role(Role.USER)
                .build();
        if(user.isValid())
           repository.save(user);
        else throw new Exception();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }


    // Prove of who is visitor? question (authenticate)
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
       authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                ));
        var user=repository.findByEmail(request.getEmail());
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
