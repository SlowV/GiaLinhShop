package com.slowv.fruit.web.rest.admin;

import com.slowv.fruit.domain.rest.Response;
import com.slowv.fruit.service.ProductService;
import com.slowv.fruit.service.dto.ProductDto;
import com.slowv.fruit.service.dto.request.ProductCreateDto;
import com.slowv.fruit.service.mapper.ProductMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/admin/product")
public class ProductResource {

    private final ProductService productService;

    private final ProductMapper productMapper;

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Response<ProductDto> createProduct(ProductCreateDto dto) {
        return Response.ok(productMapper.toDto(productService.store(dto)));
    }
}
