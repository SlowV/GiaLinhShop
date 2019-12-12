package com.gialinhnail.shop.service;

import com.gialinhnail.shop.enity.Category;
import com.gialinhnail.shop.repo.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    private static Logger LOGGER = LoggerFactory.getLogger(CategoryService.class.getSimpleName());

    public Category save(Category category){
       return categoryRepository.save(category);
    }

    public Page<Category> categories(int page){
        int size = 2; //default page size is 10
        LOGGER.info(String.valueOf(page));
        return categoryRepository.findAll(PageRequest.of(page, size));
    }
}
