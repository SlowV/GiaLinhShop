package com.slowv.fruit.service;

import com.slowv.fruit.domain.Category;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {
    Category save(Category category);

    Page<Category> findAll(int page, int size);

    List<Category> findAll();

    List<Category> findByStatus(int status);

    Category findById(long id);
}
