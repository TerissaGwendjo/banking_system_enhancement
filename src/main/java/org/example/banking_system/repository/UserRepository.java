package org.example.banking_system.repository;

import org.example.banking_system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Indicates that this interface is a repository component managed by Spring
public interface UserRepository extends JpaRepository<User, Long> {
    /*
    * User is the entity class representing the user data.
    *Long is the type of the primary key of the User entity.
    * */

    // Declares a method to find a user by username
    User findUserByUsername(String username);
}
