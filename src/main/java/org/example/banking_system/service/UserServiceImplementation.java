package org.example.banking_system.service;

import jakarta.transaction.Transactional;
import org.example.banking_system.model.Account;
import org.example.banking_system.model.User;
import org.example.banking_system.model.VerificationToken;
import org.example.banking_system.repository.UserRepository;
import org.example.banking_system.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImplementation implements UserService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    //passwordEncoder is like a specific hashing code which is a critical aspect of securing user passwords.
    //So there is no way to come back to the password through it. So the database will never return to the original password

    @Autowired
    private VerificationTokenRepository tokenRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    @Transactional
    public boolean saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); //getPassword is the original password and the setPassword is the hash password
        try {
            userRepository.save(user);
            sendVerificationEmail(user);

        } catch (MailException e) { // MailException will be thrown if email is not sent
            //log in exception (optional)
            System.out.println("Fail to send Verification Email:" + e.getMessage());
            return false;
        }
        return true;
        //In this method we are saving the user only after the user has received the verification mail
        //if no it means there was an error and the user will not be saved

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

    public void createVerificationToken(User user, String token) {
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationToken.setExpiryDate(LocalDateTime.now().plusDays(1));
        tokenRepository.save(verificationToken);

    }

    public VerificationToken getVerificationToken (String token){
        return tokenRepository.findByToken(token);
    }

    public void verifyUser (String token){
        VerificationToken verificationToken = getVerificationToken(token);
        if (
                verificationToken != null // does exist
                        &&
                        verificationToken.getExpiryDate().isAfter(LocalDateTime.now())  // not expired
        ) {
            User user = verificationToken.getUser();
            user.setVerified(true);
            userRepository.save(user);
            //tokenRepository.delete(verificationToken); we might keep it for our future logs
        }
    }

    private void sendVerificationEmail (User user) throws MailException {
        String token = UUID.randomUUID().toString();
        createVerificationToken(user,token);
        String recipientAddress = user.getEmail();
        String subject = "Email Verification";
        String confirmationUrl = "http://localhost:7878/verify?token=" + token;
        String message = "Please click the link below to verify your email address:\n" + confirmationUrl;

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message);

        mailSender.send(email);
    }

}
