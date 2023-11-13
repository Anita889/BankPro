package com.example.work.security;

import com.example.work.repository.LoginHumanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


/*****************************************
 * Authentication configuration layer class
 *****************************************/
@Configuration
@RequiredArgsConstructor
public class ApplicationConfiguration {

    private final LoginHumanRepository loginHumanRepository;

    //get UserDetailsService marking UserDetails/Human(our security model) object as BEAN
    @Bean
    public UserDetailsService userDetailsService() {
    return loginHumanRepository::findByEmail;
    }

    //provide AuthenticationProvider marking (DaoAuthenticationProvider object) for authentication as BEAN
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProv=new DaoAuthenticationProvider();
        authProv.setUserDetailsService(userDetailsService());
        authProv.setPasswordEncoder(passwordEncoder());
        return authProv;
    }

    //provide AuthenticationManager marking for authentication manage as BEAN
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }


    //provide PasswordEncoder marking (BCryptPasswordEncoder object) for encode as BEAN
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
