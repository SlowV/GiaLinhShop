package com.slowv.fruit.web.rest.admin;

import com.slowv.fruit.domain.rest.RESTResponse;
import com.slowv.fruit.service.ProductService;
import com.slowv.fruit.service.dto.request.ProductCreateDto;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;

@AllArgsConstructor
@Controller
@RequestMapping(method = RequestMethod.GET, value = "/admin/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public HashMap<String, Object> createProduct(ProductCreateDto dto) {
        return new RESTResponse.Success()
                .setData(productService.store(dto))
                .build();
    }
}
