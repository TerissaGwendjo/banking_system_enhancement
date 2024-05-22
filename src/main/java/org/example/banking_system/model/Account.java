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




    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
