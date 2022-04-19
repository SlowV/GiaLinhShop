package com.slowv.fruit.web.rest.admin;

import com.slowv.fruit.domain.rest.Response;
import com.slowv.fruit.service.ProductService;
import com.slowv.fruit.service.dto.ProductDto;
import com.slowv.fruit.service.dto.request.ProductCreateRequest;
import com.slowv.fruit.service.mapper.ProductMapper;
import lombok.AllArgsConstructor;
import lombok.var;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/admin/product")
public class ProductResource {

    private final ProductService productService;

    private final ProductMapper productMapper;

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Response<ProductDto> createProduct(ProductCreateRequest dto) {
        return Response.ok(productMapper.toDto(productService.store(dto)));
    }

    @GetMapping
    public Response<List<ProductDto>> getProducts() {
        final var listData = productService.findAll()
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
        return Response.ok(listData);
    }

    @GetMapping("/{id}")
    public Response<ProductDto> getProduct(@PathVariable("id") Long id) {
        return Optional.ofNullable(productService.findById(id))
                .map(product -> Response.ok(productMapper.toDto(product)))
                .orElseThrow(EntityNotFoundException::new);
    }

    @PutMapping("/{id}")
    public Response<ProductDto> update(@PathVariable("id") String id) {
        return null;
    }
}
