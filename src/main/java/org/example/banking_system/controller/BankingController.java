package org.example.banking_system.controller;

import org.example.banking_system.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BankingController {

    @Autowired
    private AccountService service;

    @GetMapping("/")
    public String showHomePage() {
        return "home";
    }
}
