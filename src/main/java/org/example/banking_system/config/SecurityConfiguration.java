package org.example.banking_system.config;
//Package imports

import org.example.banking_system.service.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration{
    @Bean
    // Annotates this method as a bean that Spring should manage
    public UserDetailsService userDetailsService(UserServiceImplementation userService) {

        return userService;
        // Return the UserServiceImplementation as the UserDetailsService
    }

    @Bean

    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //allows us to define the chain of security filters
        // This method configures the HttpSecurity object, which allows customization of security settings for HTTP requests.

        http

                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        // Permit all requests to the home page and static resources
                        .requestMatchers("/signup", "/login").permitAll()
                        // Require authentication for any other request
                        .anyRequest().authenticated()

                )

                .formLogin(formLogin -> formLogin

                        .loginPage("/login") // Specifies the login page URL

                        // Redirect to the home page ("/") after successful login, always redirecting even if the user was previously navigating elsewhere
                        .defaultSuccessUrl("/", true)

                        .permitAll() // Allow everyone to see the login page

                )

                .logout(logout -> logout

                        .logoutUrl("/logout") // Specifies the logout page URL

                        // Redirect to the login page with a logout parameter after a successful logout
                        .logoutSuccessUrl("/login?logout")

                        .permitAll()

                );
        // Builds and returns the SecurityFilterChain
        return http.build();

    }



    @Autowired

    public void configureGlobal(
            AuthenticationManagerBuilder auth,
            UserDetailsService userDetailsService
    ) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());

    }






/*
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
*/

}
