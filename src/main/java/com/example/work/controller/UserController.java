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

/**=============================
 * Controller of my project paths
 ===============================*/

@Slf4j
@Controller
@RequestMapping("/api")
public class UserController {
    private final UserService userService;
    private final CardService cardService;
    @Autowired
    public UserController(UserService userService, CardService cardService) {
        this.userService = userService;
        this.cardService = cardService;
    }
    //open first page
    @GetMapping("/bank")
    public String getBank()
    {
        return "bank";
    }
    //sent to front user(bank user) login page
    @GetMapping("/bank/user-login")
    public String getUserLogin(Model model)
    {
        UserDto userDto=UserMap.model_Dto_User(new Users());
        model.addAttribute("user",userDto);
        return "user-login";
    }

    //check user has or not registration
    @PostMapping("/bank/user-login")
    public String passUserLogin(@ModelAttribute("user")UserDto user)
    {
        if(userService.findByEmail(user.getEmail())!=null
        && Objects.equals(userService.findByEmail(user.getEmail()).getUserPassword(), user.getUserPassword())) {
            return "redirect:/api/bank/card-login";
        }
        else return "redirect:/api/bank/user-register";
    }
    //if not has come here and make new USER registration (send page to front)
    @GetMapping("/bank/user-register")
    public String getUserReg(Model model)
    {
        Users user=new Users();
        UserDto userDto= UserMap.model_Dto_User(user);
        model.addAttribute("user",userDto);
        return  "user-register";
    }
    //if not has come here and make new USER registration (get info from  front)
    @PostMapping("/bank/user-register")
    public String postUserReg(@Valid @ModelAttribute UserDto user)
    {
        if(userService.findByEmail(user.getEmail())==null)
            userService.userSave(user);
        return "redirect:/bank";
    }

    //sent to front card(bank user card) login page

    @GetMapping("/bank/card-login")
    public String getCardLogin(Model model)
    {
        CardDto cardDto=CardMap.model_Dto_Card(new Card());
        model.addAttribute("card",cardDto);
        return "card-login";
    }
    //check user's card has or not registration
    @PostMapping("/bank/card-login")
    public String passCardLogin(@ModelAttribute("card")CardDto card)
    {
        if(cardService.findByCardPassword(card.getCardPassword())!=null) {
            return "redirect:/api/bank/payment/"+cardService.findByCardPassword(card.getCardPassword()).getId();
        }
        else return "redirect:/api/bank/card-login";
    }

    //if not has come here and make new CARD registration (send page to  front)
    @GetMapping("/bank/card-register")
    public String getCardRegister(Model model)
    {
        Card card=new Card();
        model.addAttribute("card",card);
        return "card-register";
    }

    //if not has come here and make new CARD registration (get info from  front)
    @PostMapping("/bank/card-register")
    public String  getCardRegisterSave(@RequestBody @ModelAttribute("card")CardDto card) {
            cardService.saveCard(CardMap.dto_model_Card(card));
        return  "redirect:/bank";
    }

    //if everything OK,start payment process(send page to front)
    @GetMapping("/bank/payment/{id}")
    public String paymentMethod1(@PathVariable("id") Integer cardId,Model model)
    {
        Optional<Card> card=cardService.findByCardId(cardId);
        model.addAttribute("card",card);
        return "payment";
    }

    //make payment and update that row
    @PostMapping("/bank/payment/{id}")
    public String paymentMethod2(@PathVariable("id") Integer cardId,@ModelAttribute("card") CardDto cardDto, Long pay)
    {
        Card card= cardService.findByCardId(cardId).isPresent()?cardService.findByCardId(cardId).get():null;
        cardService.doPayment(card,pay);
        return "redirect:/api/bank/account-info/"+cardId;
    }

    //show account info
    @GetMapping("/bank/account-info/{id}")
    public String showResults(@PathVariable Integer id, Model model) {
        Card card = cardService.findByCardId(id).orElse(null);
        if (card != null) {
            String accountInfo = "Your account is " + card.getAccount();
            model.addAttribute("accountInfo", accountInfo);
        } else {
            model.addAttribute("accountInfo", "Account not found");
        }
        return "payment-do";
    }

}
