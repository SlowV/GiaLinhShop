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

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    private static Logger LOGGER = LoggerFactory.getLogger(CategoryService.class.getSimpleName());

    public Category save(Category category){
       return categoryRepository.save(category);
    }

    public Page<Category> categories(int page, int size){
        LOGGER.info(String.valueOf(page));
        return categoryRepository.findAll(PageRequest.of(page, size));
    }

    public List<Category> findAllNoStatus (){
        return categoryRepository.findAll();
    }

    public List<Category> findByStatus (int status){
        return categoryRepository.findAllByStatus(status);
    }

    public void saveAll(List<Category> categories){
        for (Category category : categories) {
            categoryRepository.save(category);
        }
    }

    public Category findById(long id){
        return categoryRepository.findById(id).orElse(null);
    }
}
