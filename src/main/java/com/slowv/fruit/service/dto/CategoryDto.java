package com.slowv.fruit.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryDto {
    private long id;
    private String name;
    private String description;
    private String images;
    private String createdAt;
    private String updatedAt;
    private String deletedAt;
    private int statusCode;
    private String statusLabel;
}
