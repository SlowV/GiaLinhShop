package com.slowv.fruit.service.dto.request;

import lombok.Data;

@Data
public class AuthLoginRequest {
    private String email;
    private String password;
}
