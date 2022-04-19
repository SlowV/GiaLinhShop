package com.slowv.fruit.web.rest.admin;

import com.slowv.fruit.domain.rest.Response;
import com.slowv.fruit.service.AuthService;
import com.slowv.fruit.service.dto.request.AuthLoginRequest;
import com.slowv.fruit.service.dto.response.AuthLoginResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping(value = "/admin")
public class AuthResource {

    private final AuthService authService;

    @PostMapping("/auth/login")
    public Response<AuthLoginResponse> login(@RequestBody AuthLoginRequest authLoginRequest) {
        final var authLoginResponse = authService.login(authLoginRequest);
        return Response.ok(authLoginResponse);
    }
}
