package com.slowv.fruit.service.dto.response;

import com.slowv.fruit.service.dto.CategoryDto;
import com.slowv.fruit.service.dto.CollectionDto;
import com.slowv.fruit.service.dto.ProductDto;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class HomeResponseDTO implements Serializable {
   private final List<ProductDto> products;
   private final List<CollectionDto> collections;
   private final List<CategoryDto> categories;

}
