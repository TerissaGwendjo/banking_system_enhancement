package org.example.banking_system.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(unique = true, nullable = true)
    private String username;
    @Column(nullable = false)
    String password;

    @OneToMany (mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true) // meaning one user entity can be attributed to many accounts instances
    /* The Account entity has a field named user that references the User it belongs to. Any changes to the user
    (like saving, updating, or deleting) will automatically apply to all related orders (cascade = CascadeType.ALL). Additionally, if an order
    is removed from the user's list of orders, it will be deleted from the database to ensure no orders are left
    without an associated user (orphanRemoval = true).*/

    private List<Account> accounts; //A single user can have many eccounts,

    // Constructors
    public User() {
    }
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    //Getters and setters
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }

    //Getters and setters for accounts
    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
