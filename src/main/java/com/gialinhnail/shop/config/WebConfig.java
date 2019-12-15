package com.gialinhnail.shop.config;

import com.gialinhnail.shop.enity.Account;
import com.gialinhnail.shop.service.AccountService;
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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    AccountService accountService;
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
                Account account = accountService.findByEmail(email);
                if (account == null) {
                    throw new UsernameNotFoundException("User not found");
                }
                return User.builder()
                        .username(account.getEmail())
                        .password(account.getPassword())
                        .roles("USER")
                        .build();
            }
        };
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().disable()
                .authorizeRequests()
                .anyRequest().permitAll()
                .and().formLogin().loginPage("/account/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .and().logout();
    }
}
