package com.slowv.fruit.web.rest.admin;

import com.slowv.fruit.domain.rest.Response;
import com.slowv.fruit.service.dto.ProductDto;
import com.slowv.fruit.service.dto.request.ProductCreateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/admin/product")
public interface IProductResource {

    @PostMapping("/create")
    @Operation(description = "Thêm mới sản phẩm")
    @ApiResponse(responseCode = "200", description = "Success")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "404", description = "Not Found Exception")
    @ApiResponse(responseCode = "405", description = "Method Not Allow")
    @ApiResponse(responseCode = "409", description = "Business Validation Exception")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    Response<ProductDto> createProduct(ProductCreateRequest dto);
}
