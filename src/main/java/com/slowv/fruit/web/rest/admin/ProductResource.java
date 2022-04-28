package com.slowv.fruit.web.rest.admin;

import com.slowv.fruit.domain.rest.Response;
import com.slowv.fruit.service.ProductService;
import com.slowv.fruit.service.dto.ProductDto;
import com.slowv.fruit.service.dto.request.ProductCreateRequest;
import com.slowv.fruit.service.dto.request.ProductUpdateRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class ProductResource implements IProductResource{

    private final ProductService productService;

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Response<ProductDto> createProduct(ProductCreateRequest dto) {
        return Response.ok(productService.store(dto));
    }

    @GetMapping
    public Response<List<ProductDto>> getProducts() {
        return Response.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public Response<ProductDto> getProduct(@PathVariable("id") Long id) {
        return Response.ok(productService.findById(id));
    }

    @PutMapping
    public Response<ProductDto> update(ProductUpdateRequest request) {
        return Response.ok(productService.update(request));
    }
}
