package org.example.banking_system.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Account {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private Long id;

    private String AccountNumber; // provided by the bank system and not ID
    private String AccountType;
    private Double balance;

    //Constructors

    public Account() {
    }

    public Account(Long id, String accountNumber, String accountType, Double balance) {
        this.id = id;
        AccountNumber = accountNumber;
        AccountType = accountType;
        this.balance = balance;
    }

    public Account(String accountNumber, String accountType, Double balance) {
        AccountNumber = accountNumber;
        AccountType = accountType;
        this.balance = balance;
    }

    // Getters and setters
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public String getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        AccountNumber = accountNumber;
    }

    public String getAccountType() {
        return AccountType;
    }

    public void setAccountType(String accountType) {
        AccountType = accountType;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
