package org.example.banking_system.service;

import org.example.banking_system.model.Account;
import org.example.banking_system.repository.AccountRepository;
import org.example.banking_system.util.AccountNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BankingServiceImplementation implements AccountService{

    // Autowired field for accessing the AccountRepository
    @Autowired
    private AccountRepository repository;

    // Method to open a new account
    @Override
    @Transactional
    @Modifying
    public void openNewAccount(String accountType, double initialDeposit) {

        // Create a new account object
        Account account = new Account();
        // Set a generated account number
        account.setAccountNumber(AccountNumberGenerator.generateAccountNumber());
        // Set the account type
        account.setAccountType(accountType);
        // Set the initial balance
        account.setBalance(initialDeposit);
        // Save the account in the repository
        repository.save(account);
    }

    // Method to deposit money into an account
    @Override
    @Transactional
    @Modifying
    public void depositMoney(String accountNumber, double amount) {
        // Find the account by account number
        Account account = repository.findByAccountNumber(accountNumber);
        // If account exists
        if(account != null){
            // Add the deposited amount to the account balance
            account.setBalance(account.getBalance() + amount);
            // Save the updated account
            repository.save(account);
        }else{
            // If account does not exist, throw an exception
            throw new RuntimeException("Account not exist");
        }
    }

    // Method to withdraw money from an account
    @Override
    @Transactional
    @Modifying
    public void withdrawMoney(String accountNumber, double amount) {

        // Find the account by account number
        Account account = repository.findByAccountNumber(accountNumber);
        // If account exists and has sufficient funds
        if(account != null && account.getBalance() >= amount){
            // Deduct the withdrawn amount from the account balance
            account.setBalance(account.getBalance() - amount);
            // Save the updated account
            repository.save(account);
        }else{
            // If account does not exist or has insufficient funds, throw an exception
            throw new RuntimeException("Account not exist or insufficient funds for withdrawal");
        }
    }

    // Method to transfer money between accounts
    @Override
    @Transactional
    @Modifying
    public void transferMoney(String fromAccount, String toAccount, double amount) {
        // Withdraw money from the sender's account
        withdrawMoney(fromAccount, amount);
        // Deposit money into the recipient's account
        depositMoney(toAccount, amount);
    }
}

