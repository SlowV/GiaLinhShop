package com.slowv.fruit.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryDto {
    private long id;
    private String name;
    private String description;
    private String images;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;
    private int statusCode;
    private String statusLabel;
}
