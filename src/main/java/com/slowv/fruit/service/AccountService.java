package com.slowv.fruit.service;

import com.slowv.fruit.domain.Account;

public interface AccountService {
    Account register(Account account);
    Account findByEmail (String email);
}
