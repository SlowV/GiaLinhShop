package com.slowv.fruit.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountDto {
    private Long id;
    private String email;
    private String fullName;
    private String phoneNumber;
    private String address;
    private String introduction;
    private Instant createdDate;
    private Instant updatedDate;
    private int statusCode;
    private String statusLabel;
}
