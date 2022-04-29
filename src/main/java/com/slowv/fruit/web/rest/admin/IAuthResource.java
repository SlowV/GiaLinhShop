package com.slowv.fruit.web.rest.admin;

import com.slowv.fruit.constant.HeaderRequestParam;
import com.slowv.fruit.domain.rest.Response;
import com.slowv.fruit.service.dto.request.AuthLoginRequest;
import com.slowv.fruit.service.dto.request.ForgotPasswordRequest;
import com.slowv.fruit.service.dto.request.ResetPasswordRequest;
import com.slowv.fruit.service.dto.request.ValidateResetPasswordRequest;
import com.slowv.fruit.service.dto.response.AuthLoginResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping(value = "/admin/auth")
public interface IAuthResource {

    @PostMapping("/login")
    @Operation(description = "User Login")
    @ApiResponse(responseCode = "200", description = "Success")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "404", description = "Not Found Exception")
    @ApiResponse(responseCode = "405", description = "Method Not Allow")
    @ApiResponse(responseCode = "409", description = "Business Validation Exception")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    Response<AuthLoginResponse> login(@RequestBody AuthLoginRequest authLoginRequest);

    @PostMapping("/logout")
    @Operation(description = "User logout")
    @ApiResponse(responseCode = "200", description = "Success")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "404", description = "Not Found Exception")
    @ApiResponse(responseCode = "405", description = "Method Not Allow")
    @ApiResponse(responseCode = "409", description = "Business Validation Exception")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    Response<String> logout(@RequestHeader(HeaderRequestParam.USER_ID) Long id);

    @PostMapping("/forgot")
    @Operation(description = "Send email with link reset password")
    @ApiResponse(responseCode = "200", description = "Success")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "404", description = "Not Found Exception")
    @ApiResponse(responseCode = "405", description = "Method Not Allow")
    @ApiResponse(responseCode = "409", description = "Business Validation Exception")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    Response<Object> forgotPassword(@RequestBody @Valid ForgotPasswordRequest request);

    @PostMapping("/reset")
    @Operation(description = "Change password by link reset password")
    @ApiResponse(responseCode = "200", description = "Success")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "404", description = "Not Found Exception")
    @ApiResponse(responseCode = "405", description = "Method Not Allow")
    @ApiResponse(responseCode = "409", description = "Business Validation Exception")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    Response<String> resetPassword(@RequestBody @Valid ResetPasswordRequest request);

    @PostMapping("/validate")
    @Operation(description = "Validate link reset password")
    @ApiResponse(responseCode = "200", description = "Success")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "404", description = "Not Found Exception")
    @ApiResponse(responseCode = "405", description = "Method Not Allow")
    @ApiResponse(responseCode = "409", description = "Business Validation Exception")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    Response<Object> validateResetPassword(@RequestBody @Valid ValidateResetPasswordRequest request);
}
