package org.example.banking_system.service;

import jakarta.transaction.Transactional;
import org.example.banking_system.model.User;
import org.example.banking_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends UserDetailsService {

    //save new user
    /*
    * @Description: save new user
    * @param usernames: String and unique username
    * @param password: String value
    * return no specific value: */

    @Transactional
    boolean saveUser(User user);

    //find user by username
    User findByUserName(String username);

    boolean userCheckPassword (String rawPassword, String encodedPassword);





}
