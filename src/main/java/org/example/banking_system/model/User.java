package org.example.banking_system.model;

import jakarta.persistence.*;

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
}
