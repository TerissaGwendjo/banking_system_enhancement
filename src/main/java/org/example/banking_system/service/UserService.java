package org.example.banking_system.service;

import org.example.banking_system.model.User;
import org.example.banking_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    //register user : save user
    public void registerUser (String username, String password) {
        User user = new User(username,password);
        userRepository.save(user);
    }

    //log in user: find user by username
    public String logInUser (String username, String password) { //it returns a string to return just the username, we could also use boolean
        //User user = new User(); // empty user cus i don't know if a user already exists or not

        User user = userRepository.findUserByUsername(username);

        if (user == null) {
            throw  new IllegalArgumentException("Bad credentials!!");
        }
        if (!password.equals(user.getPassword())) {
            throw  new IllegalArgumentException("Bad credentials!!");
        }
        return user.getUsername();
    }

    // edit user credentials: username or password





}
