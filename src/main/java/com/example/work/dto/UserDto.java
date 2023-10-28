package com.example.work.dto;

import com.example.work.models.Card;
import com.example.work.models.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;


@Data
public class UserDto  {
    private Integer id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String surname;
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
    private Role role;
    private List<Card> cards;

}
