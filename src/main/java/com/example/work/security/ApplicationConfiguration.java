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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfiguration {

    private final LoginHumanRepository loginHumanRepository;
    @Bean
    public UserDetailsService userDetailsService(){
        return  username -> loginHumanRepository.findByEmail(username)
                .orElseThrow(()->new UsernameNotFoundException("User not find"));
    }
    @Bean
    public AuthenticationProvider authenticationProviderBy(){
        DaoAuthenticationProvider authProv=new DaoAuthenticationProvider();
        authProv.setUserDetailsService(userDetailsService());
        authProv.setPasswordEncoder(passwordEncoder());
        return authProv;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
