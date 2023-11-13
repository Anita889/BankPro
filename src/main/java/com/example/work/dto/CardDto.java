package com.example.work.dto;

import com.example.work.models.Users;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.sql.Timestamp;

/*************************************
 * Card model class Data Transfer class
 **************************************/

@Data
@Builder
public class CardDto {
    private Integer id;
    @NotEmpty(message = "Card name  not be empty")
    private String name;
    @NotEmpty(message = "Card surname  not be empty")
    private String surname;
    @NotEmpty(message = "Card email  not be empty")
    private String email;
    @NotEmpty(message = "Card cardPassword  not be empty")
    private String cardPassword;
    private Long pincode;
    private Long account;
    private Timestamp start;
    private Timestamp finish;
    @NotEmpty
    private Users user;
}
