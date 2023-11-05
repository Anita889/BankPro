package com.example.work.dto;

import com.example.work.models.Card;
import com.example.work.models.Role;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
public class UserDto  {
    private Integer id;
    @NotEmpty(message = "Card name  not be empty")
    private String name;
    @NotEmpty(message = "Card surname  not be empty")
    private String surname;
    @NotEmpty(message = "Card email  not be empty")
    private String email;
    @NotEmpty(message = "Card userPassword  not be empty")
    private String userPassword;
    private Role role;
    private List<Card> cards;
}
