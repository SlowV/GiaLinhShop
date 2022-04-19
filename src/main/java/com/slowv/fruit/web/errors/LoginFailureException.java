package com.slowv.fruit.web.errors;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LoginFailureException extends RuntimeException {
    public LoginFailureException(String message) {
        super(message);
    }
}
