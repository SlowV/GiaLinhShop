package com.gialinhnail.shop.service;

import com.gialinhnail.shop.enity.Product;
import com.gialinhnail.shop.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    public Product save(Product product){
        return productRepository.save(product);
    }

    public List<Product> findAllNoPage(){
        return productRepository.findAll();
    }

    public Page<Product> products(int page, int size, Direction direction){
        return productRepository.findAll(PageRequest.of(page, size, direction, "CreatedAt"));
    }

}
