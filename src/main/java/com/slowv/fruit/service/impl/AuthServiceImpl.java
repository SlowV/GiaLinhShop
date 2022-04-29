package com.slowv.fruit.service.impl;

import com.slowv.fruit.config.ApplicationProperties;
import com.slowv.fruit.constant.AppConstants;
import com.slowv.fruit.constant.TokenType;
import com.slowv.fruit.repository.AccountRepository;
import com.slowv.fruit.service.AuthService;
import com.slowv.fruit.service.EmailService;
import com.slowv.fruit.service.dto.request.AuthLoginRequest;
import com.slowv.fruit.service.dto.request.ForgotPasswordRequest;
import com.slowv.fruit.service.dto.response.AuthLoginResponse;
import com.slowv.fruit.service.mapper.AccountMapper;
import com.slowv.fruit.util.JwtUtils;
import com.slowv.fruit.web.errors.LoginFailureException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@AllArgsConstructor
@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    private final AccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder;

    private final ApplicationProperties properties;

    private final AccountMapper accountMapper;

    private final StringRedisTemplate redisTemplate;

    private final EmailService emailService;

    @Override
    public AuthLoginResponse login(AuthLoginRequest req) {
        final var accountExist = accountRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new LoginFailureException("Login failure"));
        if (!isMatchPassword(accountExist.getPassword(), req.getPassword())) {
            throw new LoginFailureException("Login failure");
        }
        final var authLoginResponse = new AuthLoginResponse();
        authLoginResponse.setInfo(accountMapper.toDto(accountExist));
        final var token = JwtUtils.generateToken(TokenType.ACCESS_TOKEN, properties.getJwtSecret(), accountExist.getUuid(), properties.getJwtExpiration());
        authLoginResponse.setToken(token);
        redisTemplate.opsForValue().set(String.valueOf(accountExist.getId()), token);
        return authLoginResponse;
    }

    @Override
    public void logout(Long id) {
        final var accountExist = accountRepository.findById(id)
                .orElseThrow(() -> new LoginFailureException("Login failure"));
        redisTemplate.delete(String.valueOf(accountExist.getId()));
    }

    @Override
    public void forgotPassword(ForgotPasswordRequest request) {
        final var account = accountRepository.findByEmail(request.getEmail())
                .orElseThrow(EntityNotFoundException::new);
        final var token = JwtUtils.generateToken(TokenType.RESET_TOKEN, properties.getJwtSecret(), account.getUuid(), properties.getResetTokenExpire());

        final var cacheKey = AppConstants.RESET_TOKEN_CACHE_PREFIX + account.getId();
        redisTemplate.opsForValue().set(cacheKey, token);
        emailService.sendResetPassword(account, token);
    }

    private boolean isMatchPassword(String pass1, String pass2) {
        return passwordEncoder.matches(pass2, pass1);
    }
}
