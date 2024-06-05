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

                        .requestMatchers("/signup", "/login").permitAll()

                        .anyRequest().authenticated()

                )

                .formLogin(formLogin -> formLogin

                        .loginPage("/login")

                        .defaultSuccessUrl("/", true)

                        .permitAll()

                )

                .logout(logout -> logout

                        .logoutUrl("/logout")

                        .logoutSuccessUrl("/login?logout")

                        .permitAll()

                );



        return http.build();

    }



    @Autowired

    public void configureGlobal(AuthenticationManagerBuilder auth, UserDetailsService userDetailsService) throws Exception {

        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());

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
