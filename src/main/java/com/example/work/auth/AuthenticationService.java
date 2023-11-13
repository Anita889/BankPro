package com.example.work.auth;



import com.example.work.models.Human;
import com.example.work.models.Role;
import com.example.work.repository.LoginHumanRepository;
import com.example.work.security.JWTService;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;




/*********************************
 **Authentication and Registration
 **processes service layer class
 *********************************/

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final LoginHumanRepository repository;

    private final PasswordEncoder passwordEncoder;

    private final JWTService jwtService;

    private final AuthenticationManager authenticationManager;



    /*********************************
     **Registration of new visitor
     *********************************/
    public AuthenticationResponse register(RegisterRequest request){
        //make role field initialize
        String email=request.getEmail();
            if (email.contains("%bank@gmail.com"))
                request.setRole(Role.ADMIN);
            else request.setRole(Role.USER);
        //build new user
        Human user = Human.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
            repository.save(user);

            //generate jwt token and make response of http request
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .responseRole(request.getRole())
                .build();
    }
     /*************************************************
     // Prove of who is visitor? question (authenticate)
     //************************************************/
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        //authenticate request
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                ));
        var user=repository.findByEmail(request.getEmail());
        var jwtToken = jwtService.generateToken(user);

        //generate jwt token and make response of http request
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .responseRole(user.getRole())
                .build();
    }
}
