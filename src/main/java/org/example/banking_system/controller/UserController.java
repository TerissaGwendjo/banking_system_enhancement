package org.example.banking_system.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.banking_system.service.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired

    private UserServiceImplementation userService;

    // Handles GET requests to the "/login" URL
    @GetMapping("/login")
    public String showLoginForm( Model model) {
            // Adds a default error message to the model
            model.addAttribute("errorMessage", "Invalid credentials");

        // Returns the "login" view
        return "login";
    }

    // Handles GET requests to the "/signup" URL
    @GetMapping("/signup")
    public String showRegistrationForm() {
        // Returns the "signup" view
        return "signup";
    }

    // Handles POST requests to the "/signup" URL
    @PostMapping("/signup")
    public String signUp(@RequestParam String username, @RequestParam String password) {
        // Calls the userService to save the new user with the provided username and password
        userService.saveUser(username, password);

        // Redirects to the login page with a success query parameter
        return "redirect:/login?success=true";
    }

    @GetMapping("/logout")
    public String logout() {

        return "redirect:/login?logout"; // Redirect to login page with logout parameter
    }

}
