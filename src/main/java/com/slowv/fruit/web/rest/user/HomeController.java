package com.slowv.fruit.web.rest.user;

import com.slowv.fruit.service.dto.response.HomeResponseDTO;
import com.slowv.fruit.service.impl.CategoryServiceImpl;
import com.slowv.fruit.service.impl.CollectionServiceImpl;
import com.slowv.fruit.service.impl.ProductServiceImpl;
import com.slowv.fruit.service.mapper.CategoryMapper;
import com.slowv.fruit.service.mapper.CollectionMapper;
import com.slowv.fruit.service.mapper.ProductMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/")
@AllArgsConstructor
@Slf4j
public class HomeController {

    private final CategoryServiceImpl categoryServiceImpl;

    private final CollectionServiceImpl collectionServiceImpl;

    private final ProductServiceImpl productServiceImpl;

    private final CategoryMapper categoryMapper;
    private final CollectionMapper collectionMapper;
    private final ProductMapper productMapper;

    @GetMapping
    public HomeResponseDTO index(Model model) {
        var categories = categoryServiceImpl.findByStatus(1)
                .stream().map(categoryMapper::toDto)
                .collect(Collectors.toList());
        var collections = collectionServiceImpl.findAllNoPage()
                .stream().map(collectionMapper::toDto)
                .collect(Collectors.toList());
        var products = productServiceImpl.products(0, 10, Sort.Direction.DESC).getContent()
                .stream().map(productMapper::toDto)
                .collect(Collectors.toList());
        return new HomeResponseDTO(products, collections, categories);
    }

    @GetMapping(value = "/products")
    public String products(Model model) {
        model.addAttribute("categories", categoryServiceImpl.findByStatus(1));
        return "user/products";
    }
}
