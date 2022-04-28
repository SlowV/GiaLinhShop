package com.slowv.fruit.service;

import com.slowv.fruit.domain.Product;
import com.slowv.fruit.service.dto.ProductDto;
import com.slowv.fruit.service.dto.request.ProductCreateRequest;
import com.slowv.fruit.service.dto.request.ProductUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ProductService {
    ProductDto store(ProductCreateRequest dto);
    Page<ProductDto> findAll(int page, int size, Sort.Direction direction);
    List<ProductDto> findAll();
    ProductDto findById(long id);
    ProductDto update(ProductUpdateRequest dto);
}
