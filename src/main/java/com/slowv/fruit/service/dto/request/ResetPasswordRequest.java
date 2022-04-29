package com.slowv.fruit.service.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResetPasswordRequest {
    @Schema(description = "Token in link reset password")
    String token;

    @Schema(description = "New password")
    String newPassword;

    @Schema(description = "Confirm password to change")
    String confirmPassword;
}