package org.example.banking_system.controller;

import org.example.banking_system.model.User;
import org.example.banking_system.service.BankingService;
import org.example.banking_system.service.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountController {
    @Autowired // Injecting the BankingService dependency
    private BankingService bankingService;

    @Autowired // Injecting the UserServiceImplementation dependency
    private UserServiceImplementation userService;

    @GetMapping("/") // Mapping for the home page
    public String showHomePage() {
        return "home"; // Returns the view name for the home page
    }

    @GetMapping("/openAccount") // Mapping for displaying the account opening form
    public String showOpenAccountForm() {
        return "openAccount"; // Returns the view name for the account opening form
    }

    @PostMapping("/openAccount") // Mapping for handling account opening form submissions
    public String openNewAccount(@RequestParam String accountType, @RequestParam double initialDeposit, @AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userService.findByUserName(userDetails.getUsername()); // Retrieves the user by username
        bankingService.openNewAccount(accountType, initialDeposit, user.getId()); // Opens a new account with the provided details
        model.addAttribute("message", "Account successfully created!"); // Adds a success message to the model
        return "redirect:/accountSuccess"; // Redirects to the account success page
    }

    @GetMapping("/accountSuccess") // Mapping for the account success page
    public String showAccountSuccess(){
        return "accountSuccess"; // Returns the view name for the account success page
    }

    @GetMapping("/transactions/deposit") // Mapping for displaying the deposit form
    public String showDepositForm() {
        return "deposit"; // Returns the view name for the deposit form
    }

    @PostMapping("/transactions/deposit") // Mapping for handling deposit form submissions
    public String depositMoney(@RequestParam String accountNumber, @RequestParam double amount) {
        bankingService.depositMoney(accountNumber, amount); // Deposits the specified amount into the account
        return "redirect:/"; // Redirects to the home page
    }

    @GetMapping("/transactions/withdraw") // Mapping for displaying the withdrawal form
    public String showWithdrawForm() {
        return "withdraw"; // Returns the view name for the withdrawal form
    }

    @PostMapping("/transactions/withdraw") // Mapping for handling withdrawal form submissions
    public String withdrawMoney(@RequestParam String accountNumber, @RequestParam double amount) {
        bankingService.withdrawMoney(accountNumber, amount); // Withdraws the specified amount from the account
        return "redirect:/"; // Redirects to the home page
    }

    @GetMapping("/transactions/transfer") // Mapping for displaying the transfer form
    public String showTransferForm() {
        return "transfer"; // Returns the view name for the transfer form
    }

    @PostMapping("/transactions/transfer") // Mapping for handling transfer form submissions
    public String transferMoney(@RequestParam String fromAccount, @RequestParam String toAccount, @RequestParam double amount) {
        bankingService.transferMoney(fromAccount, toAccount, amount); // Transfers the specified amount from one account to another
        return "redirect:/"; // Redirects to the home page
    }

}
