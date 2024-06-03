package org.example.banking_system.config;
//Package imports
import org.example.banking_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration{
    @Autowired
    private UserService userService;

    @Bean // implicates that the method password Encoder returns a new instance of the password encoder class
    // A bean in Spring is an object created, configured, and managed by the Spring IoC container.
    // Inversion of Control (IoC) is a design principle in software engineering where the control of object creation
    // and dependency management is transferred from the application code to a container or framework.
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    protected void configure(HttpSecurity httpSecurity)  {}
    //protected meaning it can be accessible only within the package
            //or subclasses even if the subclasses aren't in the package.

}
