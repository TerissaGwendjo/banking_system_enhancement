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
    @Autowired
    private AccountRepository repository;

    @Override
    @Transactional
    public void openNewAccount(String accountType, double initialDeposit) {
        Account account = new Account();
        account.setAccountNumber(AccountNumberGenerator.generateAccountNumber());
        account.setAccountType(accountType);
        account.setBalance(initialDeposit);
        repository.save(account);

    }

    @Override
    @Transactional
    @Modifying
    public void depositMoney(String accountNumber, double amount) {
        Account account = repository.findByAccountNumber(accountNumber);
        if (account != null) {
            account.setBalance(account.getBalance() + amount);
            repository.save(account);
        }
        //Handle the case when the account number does not exist


    }

    @Override
    @Transactional
    @Modifying
    public void withdrawMoney(String accountNumber, double amount) {
        Account account = repository.findByAccountNumber(accountNumber);
        if (account != null && account.getBalance() >= amount) {
            account.setBalance(account.getBalance() - amount);
            repository.save(account);
        } else {
            throw new RuntimeException("Account does not exist or insufficient funds for withdrawal!");
        }

    }

    @Override
    @Transactional
    @Modifying
    public void transferMoney(String fromAccount, String toAccount, double amount) {
        withdrawMoney(fromAccount, amount);
        depositMoney(toAccount,amount);
    }
}
