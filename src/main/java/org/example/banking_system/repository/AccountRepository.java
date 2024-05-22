package org.example.banking_system.repository;

import org.example.banking_system.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
/**
 * This interface extends JpaRepository provided by Spring Data JPA.
 * JpaRepository provides basic CRUD (Create, Read, Update, Delete) operations
 * for entities.
 */
public interface AccountRepository extends JpaRepository <Account, Long> {
    Account findByAccountNumber(String accountNumber);

    /**
     * This method finds an Account entity based on the provided account number.
     * It leverages Spring Data JPA's findBy convention to automatically generate
     * a query based on the method name and parameter.
     *
     * @param accountNumber The unique identifier for the account to be retrieved.
     * @return An Account object if found, null otherwise.
     */
}
