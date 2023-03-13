package com.stpc2.electronic_journal.config;

import com.stpc2.electronic_journal.serviceImpl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity //Включить и сконфигурировать Web Security
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;

    @Override //Переопределение метода конфигурации доступа к ресурсам
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests() //Включить авторизацию
                    .antMatchers("/user/login", "/static/**").permitAll() //пути для полного доступа
                    .anyRequest().authenticated() //для всех остальных запросов требуется авторизация
                .and()
                    .formLogin()   //включаем форму логина
                    .loginPage("/user/login") // страница входа
                    .permitAll() //по адресу /login разрешаем доступ для всех
                    .defaultSuccessUrl("/electronicJournal/index").failureUrl("/login?error").permitAll()
                .and()
                    .logout()
                    .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
}
