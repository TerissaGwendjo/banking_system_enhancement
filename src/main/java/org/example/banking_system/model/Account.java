package org.example.banking_system.model;

import jakarta.persistence.*;

@Entity
@Table (name = "accounts") // the table should be in plural
// The @Entity annotation tells the JPA provider (such as Hibernate) that this class is to be mapped to a database table.
// The fields of the class correspond to columns in the table.
public class Account {

    @Id // signifies it is a primary key
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private Long id; // Unique identifier for the account

    @Column (unique = true, nullable = false)
    private String accountNumber; // provided by the bank system and not ID and should be unique and NOT NULL
    private String accountType;
    private Double balance;

    @ManyToOne //  indicates that the field user represents a many-to-one relationship
    @JoinColumn(name = "user_id" , nullable = false) //This annotation tells JPA that a foreign key column exists in the
    // Expense table to reference the primary key of another table.
    private User user;

    //Getters and setters for the user
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    //default constructor
    public Account() {
        // This constructor doesn't initialize any fields,
        // it simply creates an instance of Account with default values (null for objects and 0 for primitives)
    }

    // Parameterized constructor that initializes all fields
    public Account(Long id, String accountNumber, String accountType, Double balance, User user) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
        this.user = user;
    }

    // Parameterized constructor that initializes all fields except id
    public Account(String accountNumber, String accountType, Double balance, User user) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
        this.user = user;
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
