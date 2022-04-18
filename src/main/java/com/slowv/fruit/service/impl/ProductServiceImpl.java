package com.slowv.fruit.service.impl;

import com.slowv.fruit.config.ApplicationProperties;
import com.slowv.fruit.config.MinioConfig;
import com.slowv.fruit.domain.Product;
import com.slowv.fruit.integration.minio.MinioService;
import com.slowv.fruit.repository.CategoryRepository;
import com.slowv.fruit.repository.CollectionRepository;
import com.slowv.fruit.repository.ProductRepository;
import com.slowv.fruit.service.ProductService;
import com.slowv.fruit.service.dto.ProductDto;
import com.slowv.fruit.service.dto.request.ProductCreateDto;
import com.slowv.fruit.service.dto.request.ProductUpdateDto;
import com.slowv.fruit.service.mapper.ProductMapper;
import com.slowv.fruit.web.errors.BusinessException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.BeanUtils;
import org.springframework.core.SpringProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.ObjectUtils;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    private final MinioService minioService;

    private final MinioConfig minioConfig;

    private final ProductMapper productMapper;

    private final CategoryRepository categoryRepository;

    private final CollectionRepository collectionRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Page<Product> findAll(int page, int size, Direction direction) {
        return productRepository.findAll(PageRequest.of(page, size, direction, "CreatedAt"));
    }

    @Override
    public Product findById(long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product update(ProductUpdateDto dto) {
        if (Objects.isNull(dto.getId())) {
            throw new BusinessException(400, "Id product do not exist!");
        }

        if (Objects.isNull(findById(dto.getId()))) {
            throw new BusinessException(400, "Id product do not exist!");
        }
        final var product = productMapper.toEntity(dto);
        return productRepository.save(product);
    }

    @Override
    public Product store(ProductCreateDto dto) {
        final var product = new Product();
        BeanUtils.copyProperties(dto, product);
        var images = minioService.upload(minioConfig.getBucket(), product.getCreatedDate().toEpochMilli(), dto.getImages());
        log.info("IMAGES PATH " + images);
        product.setImages(images);
        log.info("Product: " + product.toString());

        if (Objects.nonNull(dto.getCategoryId())) {
            final var category = categoryRepository.getById(dto.getCategoryId());
            product.setCategory(category);
        }

        if (Objects.nonNull(dto.getCollectionId())) {
            final var collection = collectionRepository.getById(dto.getCollectionId());
            product.setCollection(collection);
        }

        return productRepository.save(product);
    }
}
