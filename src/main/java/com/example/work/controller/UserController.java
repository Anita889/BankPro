package com.example.work.controller;

import com.example.work.dto.CardDto;
import com.example.work.dto.UserDto;
import com.example.work.mapping.CardMap;
import com.example.work.mapping.UserMap;
import com.example.work.models.Card;
import com.example.work.models.Users;
import com.example.work.service.CardService;
import com.example.work.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;

@Slf4j
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
        Users user=new Users();
        UserDto userDto= UserMap.model_Dto_User(user);
        model.addAttribute("user",userDto);
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
    public String getCardLogin(Model model)
    {
        CardDto cardDto=CardMap.model_Dto_Card(new Card());
        model.addAttribute("card",cardDto);
        return "card-login";
    }
    @PostMapping("/bank/card-login")
    public String passCardLogin(@ModelAttribute("card")CardDto card)
    {
        if(cardService.findByCardPassword(card.getCardPassword())!=null&&
                Objects.equals(cardService.findByCardPassword(card.getCardPassword()).getCardPassword(), card.getCardPassword())) {
                log.info("card password" + card.getCardPassword());
            return "payment";
        }
        else return "redirect:/bank";
    }
    @GetMapping("/bank/card-register")
    public String getCardRegister(Model model)
    {
        Card card=new Card();
        model.addAttribute("card",card);
        return "card-register";
    }
    @PostMapping("/bank/card-register")
    public String  getCardRegisterSave(@RequestBody @ModelAttribute("card")CardDto card) {
            cardService.saveCard(CardMap.dto_model_Card(card));
        return  "redirect:/bank";
    }
    @GetMapping("/bank/payment/{id}")
    public String paymentMethod1(@PathVariable("id") Integer cardId,Model model)
    {
        Optional<Card> card=cardService.findByCardId(cardId);
        model.addAttribute("card",card);
        return "payment";
    }
    @PostMapping("/bank/payment/{id}")
    public String paymentMethod2(@PathVariable("id") Integer cardId,Integer pay)
    {
        Optional<Card> card=cardService.findByCardId(cardId);
        if(cardService.doPayment(card,pay))
            return "payment-do";
        else
            return "payment-not-do";
    }

}
