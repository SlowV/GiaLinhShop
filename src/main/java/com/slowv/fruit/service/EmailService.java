package com.slowv.fruit.service;

import com.slowv.fruit.domain.Account;

public interface EmailService {
    void sendResetPassword(Account staff, String token);

    void sendInitialPassword(Account staff, String password);
}
