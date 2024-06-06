package org.example.banking_system.service;

import org.springframework.stereotype.Service;


/**
 * This interface defines methods for managing accounts.
 * It acts as a contract between the service implementation and the classes
 * that will use it.
 */
@Service
public interface BankingService {

    //This method simulates opening a new account with a specific type and initial deposit.
    void openNewAccount(String accountType, double initialDeposit);

    //This method simulates depositing money into an account identified by its number.
    void depositMoney (String accountNumber, double amount);

    //This method simulates withdrawing money from an account identified by its number.
    void withdrawMoney (String accountNumber, double amount);

    //This method simulates transferring money between two accounts identified by their numbers.
    void transferMoney (String fromAccount, String toAccount, double amount);
}
