package com.slowv.fruit.service;

import com.slowv.fruit.domain.Product;
import com.slowv.fruit.service.dto.request.ProductCreateDto;
import com.slowv.fruit.service.dto.request.ProductUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ProductService {
    Product store(ProductCreateDto dto);
    Page<Product> findAll(int page, int size, Sort.Direction direction);
    List<Product> findAll();
    Product findById(long id);
    Product update(ProductUpdateDto dto);
}
