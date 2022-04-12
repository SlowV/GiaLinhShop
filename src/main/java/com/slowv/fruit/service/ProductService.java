package com.slowv.fruit.service;

import com.slowv.fruit.service.dto.ProductDto;
import com.slowv.fruit.service.dto.request.ProductCreateDto;

public interface ProductService {
    ProductDto store(ProductCreateDto dto);
}
