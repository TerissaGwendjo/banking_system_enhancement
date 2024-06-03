package org.example.banking_system.model;

import jakarta.persistence.*;

@Entity
// The @Entity annotation tells the JPA provider (such as Hibernate) that this class is to be mapped to a database table.
// The fields of the class correspond to columns in the table.
public class Account {

    @Id // signifies it is a primary key
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private Long id; // Unique identifier for the account

    @Column (unique = true, nullable = false)
    private String accountNumber; // provided by the bank system and not ID and should be unique
    private String accountType;
    private Double balance;

    //default constructor
    public Account() {
        // This constructor doesn't initialize any fields,
        // it simply creates an instance of Account with default values (null for objects and 0 for primitives)
    }

    // Parameterized constructor that initializes all fields
    public Account(Long id, String accountNumber, String accountType, Double balance) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
    }

    // Parameterized constructor that initializes all fields except id
    public Account(String accountNumber, String accountType, Double balance) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
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
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
