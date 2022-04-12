package com.slowv.fruit.service.impl;

import com.slowv.fruit.config.ApplicationProperties;
import com.slowv.fruit.config.MinioConfig;
import com.slowv.fruit.domain.Product;
import com.slowv.fruit.integration.minio.MinioService;
import com.slowv.fruit.repository.ProductRepository;
import com.slowv.fruit.service.ProductService;
import com.slowv.fruit.service.dto.ProductDto;
import com.slowv.fruit.service.dto.request.ProductCreateDto;
import lombok.AllArgsConstructor;
import lombok.var;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    private final MinioService minioService;

    private final MinioConfig minioConfig;

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

    @Override
    public ProductDto store(ProductCreateDto dto) {
        final var product = new Product();
        var images = minioService.upload(minioConfig.getBucket(), product.getCreatedAt(), dto.getImages());
        product.setImages(images);
        return null;
    }
}
