package com.example.work.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;


@Component
@RequiredArgsConstructor
public class JWTAthFilter extends OncePerRequestFilter {

    private final JWTService jwtService;

    private final UserDetailsService userDetailService;
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException
    {
     final String authHeader=request.getHeader(AUTHORIZATION);
     final String jwt;
     final String userEmail;
     if(authHeader==null||!authHeader.startsWith("Bearer")){
         filterChain.doFilter(request,response);
         return;
     }
         jwt=authHeader.substring(7);
         userEmail=jwtService.extractUsername(jwt);
         if(userEmail!=null && SecurityContextHolder.getContext().getAuthentication() == null){
             UserDetails userDetails=this.userDetailService.loadUserByUsername(userEmail);
         }
    }
}
