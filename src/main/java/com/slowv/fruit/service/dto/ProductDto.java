package com.slowv.fruit.service.dto;

import lombok.Data;

@Data
public class ProductDto {
    private long id;
    private String name;
    private double unitPrice;
    private double discountPrice;
    private boolean newzz;
    private String description;
    private String images;
    private boolean isSale;
    private int perCent;
    private String detail;
    private int quantity;
    private String createdAt;
    private String updatedAt;
    private int statusCode;
    private String statusLabel;
}
