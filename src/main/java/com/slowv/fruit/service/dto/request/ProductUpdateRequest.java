package com.slowv.fruit.service.dto.request;

import lombok.Data;

import javax.persistence.Column;

@Data
public class ProductUpdateRequest {
    private Long id;
    private String name;
    private double unitPrice;
    private String description;
    private String images;
    private int perCent;
    private String detail;
    private int quantity;
    private int status;
}
