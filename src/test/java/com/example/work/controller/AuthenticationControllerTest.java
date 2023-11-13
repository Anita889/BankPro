package com.example.work.controller;


import com.example.work.auth.AuthenticationResponse;
import com.example.work.auth.AuthenticationService;
import com.example.work.auth.RegisterRequest;
import com.example.work.models.Role;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.Mockito.when;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.web.servlet.function.RequestPredicates.contentType;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(AuthenticationController.class)
@AutoConfigureMockMvc
class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private AuthenticationController authenticationController;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testRegister() throws Exception {
        // Given
        RegisterRequest registerRequest=RegisterRequest.builder()
                .password("password")
                .email("email")
                .lastname("lastname")
                .firstname("firstname")
                .role(Role.USER).build();
        AuthenticationResponse expectedResponse =AuthenticationResponse.builder()
                .responseRole(Role.USER)
                .token("hshdhbwejfbcewkejfolewehfiqhed").build();
        // Mocking the service method
        when(authenticationService.register(registerRequest)).thenReturn(expectedResponse);

        // When
        ResultActions result = mockMvc.perform(post("/api/security/register")
                .contentType(MediaType.APPLICATION_JSON)
                .contentType(objectMapper.writeValueAsString(registerRequest)));

        // Then
        result.andExpect(status().isOk())
                .andExpect(contentType().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.token").value(expectedResponse.getToken()));
    }

    @Test
    void testAuthenticate() throws Exception {
        // Given
        AuthenticationRequest authenticationRequest = new AuthenticationRequest("username", "password");
        AuthenticationResponse expectedResponse = new AuthenticationResponse("token");

        // Mocking the service method
        when(authenticationService.authenticate(authenticationRequest)).thenReturn(expectedResponse);

        // When
        ResultActions result = mockMvc.perform(post("/api/security/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(authenticationRequest)));

        // Then
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.token").value(expectedResponse.getToken()));
    }
}