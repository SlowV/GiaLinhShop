package com.slowv.fruit.service.impl;

import com.slowv.fruit.domain.Product;
import com.slowv.fruit.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl {
    private final ProductRepository productRepository;

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public List<Product> findAllNoPage() {
        return productRepository.findAll();
    }

    public Page<Product> products(int page, int size, Direction direction) {
        return productRepository.findAll(PageRequest.of(page, size, direction, "CreatedAt"));
    }

    public Product findById(long id) {
        return productRepository.findById(id).orElse(null);
    }

}
