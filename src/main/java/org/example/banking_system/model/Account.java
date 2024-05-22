package org.example.banking_system.model;

import jakarta.persistence.*;

@Entity
public class Account {

    @Id // signifies it is a primary key
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private Long id; // Unique identifier for the account

    @Column (unique = true, nullable = false)
    private String AccountNumber; // provided by the bank system and not ID and should be unique
    private String AccountType;
    private Double balance;

    //default constructor
    public Account() {
        // This constructor doesn't initialize any fields,
        // it simply creates an instance of Account with default values (null for objects and 0 for primitives)
    }

    // Parameterized constructor that initializes all fields
    public Account(Long id, String accountNumber, String accountType, Double balance) {
        this.id = id;
        AccountNumber = accountNumber;
        AccountType = accountType;
        this.balance = balance;
    }

    // Parameterized constructor that initializes all fields except id
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
