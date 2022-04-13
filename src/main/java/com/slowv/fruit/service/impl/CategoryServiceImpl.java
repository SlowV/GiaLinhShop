package com.slowv.fruit.service.impl;

import com.slowv.fruit.domain.Category;
import com.slowv.fruit.repository.CategoryRepository;
import com.slowv.fruit.service.CategoryService;
import com.slowv.fruit.service.dto.CategoryDto;
import com.slowv.fruit.service.mapper.CategoryMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Page<Category> findAll(int page, int size) {
        log.info(String.valueOf(page));
        return categoryRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> findByStatus(int status) {
        return categoryRepository.findAllByStatus(status);
    }

    public void saveAll(List<Category> categories) {
        for (Category category : categories) {
            categoryRepository.save(category);
        }
    }

    @Override
    public Category findById(long id) {
        return categoryRepository.findById(id).orElse(null);
    }
}
