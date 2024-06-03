package org.example.banking_system.service;

import org.example.banking_system.model.User;
import org.example.banking_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    //paswordEncoder is like a specific hashing code which is a critical aspect of securing user passwords.
    // So there is no way to come back to the password through it. So the database will never return to the original password

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities("USER") //we can set roles / authorities here
                .build();
    }
}
