package org.example.banking_system.service;

public interface AccountService {
    void openNewAccount(String accountType, double initialDeposit);
    void depositMoney (String accountNumber, double amount);
    void withdrawMoney (String accountNumber, double amount);
    void transferMoney (String fromAccount, String toAccount, double amount);
}
