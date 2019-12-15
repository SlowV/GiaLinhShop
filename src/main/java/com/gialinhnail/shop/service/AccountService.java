package com.gialinhnail.shop.service;

import com.gialinhnail.shop.enity.Account;
import com.gialinhnail.shop.repo.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    public Account save (Account account){
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return accountRepository.save(account);
    }

    public Account findByEmail(String email){
        return accountRepository.findByEmail(email).orElse(null);
    };
}
