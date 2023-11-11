package com.example.work.controller;


import com.example.work.auth.AuthenticationRequest;
import com.example.work.auth.AuthenticationService;
import com.example.work.auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @GetMapping("/register")
    public String getRegister(Model model){
        model.addAttribute("register", new RegisterRequest());
        return "security-register";
    }
    @PostMapping("/register")
    public void register(
            @ModelAttribute("register") RegisterRequest request)
    {
         authenticationService.register(request);
    }
    @GetMapping("/authenticate")
    public String getAuth(Model model){
        model.addAttribute("authReq",new AuthenticationRequest());
        return "security-authenticate";
    }
    @PostMapping("/authenticate")
    public void auth(
           @ModelAttribute("authReq") AuthenticationRequest request)
    {
        authenticationService.authenticate(request);
    }
}

