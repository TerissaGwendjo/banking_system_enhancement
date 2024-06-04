package org.example.banking_system.controller;

import org.example.banking_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showLoginForm(){
        return "login";
    }

    @GetMapping("/signup")
    public String showRegistrationForm(){
        return "signup";
    }

    @PostMapping ("/signup")
    public String signUp(@RequestParam String username,
                         @RequestParam String password){
        userService.saveUser(username,password);
        return "redirect:/login";
    }

}
