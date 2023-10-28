package com.example.work.controller;

import com.example.work.dto.CardDto;
import com.example.work.dto.UserDto;
import com.example.work.service.CardService;
import com.example.work.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    private final UserService userService;
    private final CardService cardService;
    @Autowired
    public UserController(UserService userService, CardService cardService) {
        this.userService = userService;
        this.cardService = cardService;
    }
    @GetMapping("/bank")
    public String getBank()
    {
        return "bank";
    }
    @GetMapping("/bank/user-login")
    public String getUserLogin()
    {
        return "user-login";
    }
    @GetMapping("/bank/user-register")
    public String getUserReg(Model model)
    {
        UserDto user=new UserDto();
        model.addAttribute("user",user);
        return  "user-register";
    }
    @PostMapping("/bank/user-register")
    public String postUserReg(@Valid @ModelAttribute UserDto user)
    {
        if(userService.findByEmail(user.getEmail())==null)
            userService.userSave(user);
        return "redirect:/bank";
    }
    @GetMapping("/bank/card-login")
    public String getCardLogin()
    {
        return "card-login";
    }

    @GetMapping("/bank/card-register")
    public String getCardRegister(Model model)
    {
        CardDto card=new CardDto();
        model.addAttribute("card",card);
        return "card-register";
    }
    @PostMapping("/bank/card-register")
    public String  getCardRegisterSave(@Valid @ModelAttribute("card")CardDto card)
    {
        if (cardService.findByPincode(card.getPincode())==null&&userService.findByEmail(card.getEmail())!=null)
            cardService.saveCard(card);
        return  "redirect:/bank";
    }
}