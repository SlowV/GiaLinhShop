package com.slowv.fruit.service.dto.response;

import com.slowv.fruit.service.dto.AccountDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class AuthLoginResponse {
    private String token;

    private AccountDto info;
}
