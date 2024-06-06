package org.example.banking_system.service;

import org.example.banking_system.model.Account;
import org.example.banking_system.model.User;
import org.example.banking_system.repository.AccountRepository;
import org.example.banking_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    //passwordEncoder is like a specific hashing code which is a critical aspect of securing user passwords.
    //So there is no way to come back to the password through it. So the database will never return to the original password

    @Override
    public void saveUser(String username, String password) {
        User user;
        user = findByUserName(username);
        if (user!=null) throw new RuntimeException("a User with this username already exists!");

        user = new User(username, passwordEncoder.encode(password));
        userRepository.save(user);

    }

    @Override
    public User findByUserName(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public boolean userCheckPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword,encodedPassword);
    }

    // rawPassword: 123456 -> encodedPassword: 9Z#@c4fpT8gRUw5

    //extra service to save user with accounts
    public void saveUserWithAccounts (String userName, String password, List<Account>accounts) {
        User user = new User(userName, passwordEncoder.encode(password));
        for (Account a: accounts) {
            a.setUser(user);
        }
        user.setAccounts(accounts);
        userRepository.save(user);
        //SO basically, for each user, we set the account and later on save the user
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Find the user by username
        User user = findByUserName(username);
        // If user is not found
        if (user == null) {
            // Throw UsernameNotFoundException
            throw new UsernameNotFoundException("User not found");
        }
        // Build and return UserDetails object with user details and authorities
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername()) // Set the username
                .password(user.getPassword()) // Set the password
                .authorities("USER") // Set authorities/roles
                .build();
    }
}
