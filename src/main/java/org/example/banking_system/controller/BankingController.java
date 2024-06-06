package org.example.banking_system.controller;

import org.example.banking_system.service.BankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BankingController {

    @Autowired // Injects the AccountService bean into this controller
    private BankingService accountService;

    @GetMapping("/") // Maps the "/" URL to this method to show the home page
    public String showHomePage() {
        return "home"; // Returns the name of the home page template
    }

    @GetMapping("/accounts/new") // Maps the "/accounts/new" URL to this method to show the new account form
    public String showNewAccountForm (){
        return "new-account"; // Returns the name of the new account form template
    }

    @PostMapping ("/accounts/new") // Maps the POST request to "/accounts/new" URL for opening a new account
    public String openNewAccountForm(
            @RequestParam String accountType, // Retrieves the account type from the form
            @RequestParam double initialDeposit // Retrieves the initial deposit amount from the form
    ) {
        accountService.openNewAccount(accountType,initialDeposit); // Calls the AccountService to open a new account
        return "redirect:/"; // Redirects the user to the home page after opening the account
    }

    @GetMapping("/transactions/deposit") // Maps the "/transactions/deposit" URL to this method to show the deposit form
    public String showDepositForm() {
        return "deposit"; // Returns the name of the deposit form template
    }

    @PostMapping("/transactions/deposit") // Maps the POST request to "/transactions/deposit" URL for depositing money
    public String depositMoney(
            @RequestParam String accountNumber, // Retrieves the account number from the form
            @RequestParam double amount // Retrieves the deposit amount from the form
    ) {
        accountService.depositMoney(accountNumber, amount); // Calls the AccountService to deposit money
        return "redirect:/"; // Redirects the user to the home page after depositing money
    }

    @GetMapping ("/transactions/withdraw") // Maps the "/transactions/withdraw" URL to this method to show the withdrawal form
    public String showWithdrawForm (){
        return "withdrawal"; // Returns the name of the withdrawal form template
    }

    @PostMapping ("/transactions/withdraw") // Maps the POST request to "/transactions/withdraw" URL for withdrawing money
    public String withDrawMoney(
            @RequestParam String accountNumber, // Retrieves the account number from the form
            @RequestParam double amount // Retrieves the withdrawal amount from the form
    ) {
        accountService.withdrawMoney(accountNumber,amount); // Calls the AccountService to withdraw money
        return "redirect:/"; // Redirects the user to the home page after withdrawing money
    }

    @GetMapping ("/transactions/transfer") // Maps the "/transactions/transfer" URL to this method to show the transfer form
    public String showTransferForm (){
        return "transfer"; // Returns the name of the transfer form template
    }

    @PostMapping ("/transactions/transfer") // Maps the POST request to "/transactions/transfer" URL for transferring money
    public String transferMoney(
            @RequestParam String fromAccount, // Retrieves the sender's account number from the form
            @RequestParam String toAccount, // Retrieves the receiver's account number from the form
            @RequestParam double amount // Retrieves the transfer amount from the form
    ){
        accountService.transferMoney(fromAccount,toAccount,amount); // Calls the AccountService to transfer money
        return "redirect:/"; // Redirects the user to the home page after transferring money
    }
}
