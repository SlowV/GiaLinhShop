package com.slowv.fruit.service.impl;

import com.slowv.fruit.config.MinioConfig;
import com.slowv.fruit.domain.Product;
import com.slowv.fruit.integration.minio.MinioService;
import com.slowv.fruit.repository.CategoryRepository;
import com.slowv.fruit.repository.CollectionRepository;
import com.slowv.fruit.repository.ProductRepository;
import com.slowv.fruit.service.ProductService;
import com.slowv.fruit.service.dto.ProductDto;
import com.slowv.fruit.service.dto.request.ProductCreateRequest;
import com.slowv.fruit.service.dto.request.ProductUpdateRequest;
import com.slowv.fruit.service.mapper.ProductMapper;
import com.slowv.fruit.web.errors.BusinessException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    public List<ProductDto> findAll() {
        return productRepository.findAll().stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<ProductDto> findAll(int page, int size, Direction direction) {
        return productRepository.findAll(PageRequest.of(page, size, direction, "CreatedAt"))
                .map(productMapper::toDto);
    }

    @Override
    public ProductDto findById(long id) {
        return productRepository.findById(id).map(productMapper::toDto).orElse(null);
    }

    @Override
    public ProductDto update(ProductUpdateRequest dto) {
        if (Objects.isNull(dto.getId())) {
            throw new BusinessException(400, "Id product do not exist!");
        }

        if (Objects.isNull(findById(dto.getId()))) {
            throw new BusinessException(400, "Id product do not exist!");
        }
        final var product = productMapper.toEntity(dto);
        return productMapper.toDto(productRepository.save(product));
    }

    @Override
    public ProductDto store(ProductCreateRequest dto) {
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

        return productMapper.toDto(productRepository.save(product));
    }
}
