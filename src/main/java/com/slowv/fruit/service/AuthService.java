package com.slowv.fruit.service;

import com.slowv.fruit.service.dto.request.AuthLoginRequest;
import com.slowv.fruit.service.dto.response.AuthLoginResponse;

public interface AuthService {
    AuthLoginResponse login(AuthLoginRequest req);
}
