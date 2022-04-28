package com.slowv.fruit.service.impl;

import com.slowv.fruit.config.ApplicationProperties;
import com.slowv.fruit.repository.AccountRepository;
import com.slowv.fruit.service.AuthService;
import com.slowv.fruit.service.dto.request.AuthLoginRequest;
import com.slowv.fruit.service.dto.response.AuthLoginResponse;
import com.slowv.fruit.service.mapper.AccountMapper;
import com.slowv.fruit.util.JwtUtils;
import com.slowv.fruit.web.errors.LoginFailureException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    private final AccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder;

    private final ApplicationProperties properties;

    private final AccountMapper accountMapper;

    @Override
    public AuthLoginResponse login(AuthLoginRequest req) {
        final var accountExist = accountRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new LoginFailureException("Login failure"));
        if (isMatchPassword(accountExist.getPassword(), req.getPassword())) {
            final var authLoginResponse = new AuthLoginResponse();
            authLoginResponse.setInfo(accountMapper.toDto(accountExist));
            final var token = JwtUtils.generateToken(properties.getJwtSecret(), accountExist.getUuid(), properties.getJwtExpiration());
            authLoginResponse.setToken(token);
            return authLoginResponse;
        }
        throw new LoginFailureException("Login failure");
    }

    private boolean isMatchPassword(String pass1, String pass2) {
        return passwordEncoder.matches(pass2, pass1);
    }
}
