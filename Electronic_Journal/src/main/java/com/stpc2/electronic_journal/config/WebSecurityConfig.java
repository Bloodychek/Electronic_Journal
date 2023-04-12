package com.stpc2.electronic_journal.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

/**
 * The class responsible for configuring security in the application
 */
@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;

    /**
     * Method that configures the login to the application
     * @param http the {@link HttpSecurity} to modify
     */
    @Override //Override resource access configuration method
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests() //Enable authorization
                    .antMatchers("/login", "/static/**").permitAll() //paths for full access
                    .anyRequest().authenticated() //authorization is required for all other requests
                .and()
                    .formLogin()   //Enable login form
                    .loginPage("/login") // login page
                    .permitAll() //at address /login allow access for everyone
                    .defaultSuccessUrl("/electronicJournal/index").failureUrl("/login?error").permitAll()
                .and()
                    .logout()
                    .permitAll();
    }

    /**
     * Method that disables password encryption
     * @param auth the {@link AuthenticationManagerBuilder} to use
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
}
