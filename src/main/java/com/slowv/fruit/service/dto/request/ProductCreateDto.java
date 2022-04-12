package com.slowv.fruit.service.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductCreateDto {
    private String name;
    private double unitPrice;
    private int perCent;
    private String description;
    private String detail;
    private String categoryId;
    private String collectionId;
    private int quantity;
    private List<MultipartFile> images = new ArrayList<>();
}
