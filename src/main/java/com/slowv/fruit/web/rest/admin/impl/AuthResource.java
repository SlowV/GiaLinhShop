package com.slowv.fruit.web.rest.admin.impl;

import com.slowv.fruit.domain.rest.Response;
import com.slowv.fruit.service.AuthService;
import com.slowv.fruit.service.dto.request.AuthLoginRequest;
import com.slowv.fruit.service.dto.request.ForgotPasswordRequest;
import com.slowv.fruit.service.dto.request.ResetPasswordRequest;
import com.slowv.fruit.service.dto.request.ValidateResetPasswordRequest;
import com.slowv.fruit.service.dto.response.AuthLoginResponse;
import com.slowv.fruit.web.rest.admin.IAuthResource;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@AllArgsConstructor
@Slf4j
@RestController
public class AuthResource implements IAuthResource {

    private final AuthService authService;

    @Override
    public Response<AuthLoginResponse> login(@RequestBody AuthLoginRequest authLoginRequest) {
        final var authLoginResponse = authService.login(authLoginRequest);
        return Response.ok(authLoginResponse);
    }

    @Override
    public Response<String> logout(Long id) {
        return null;
    }

    @Override
    public Response<Object> forgotPassword(@Valid ForgotPasswordRequest request) {
        return null;
    }

    @Override
    public Response<String> resetPassword(@Valid ResetPasswordRequest request) {
        return null;
    }

    @Override
    public Response<Object> validateResetPassword(ValidateResetPasswordRequest request) {
        return null;
    }
}
