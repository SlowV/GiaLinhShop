package com.gialinhnail.shop.service;

import com.gialinhnail.shop.enity.Product;
import com.gialinhnail.shop.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}
