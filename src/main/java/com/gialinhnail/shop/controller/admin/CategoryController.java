package com.gialinhnail.shop.controller.admin;

import com.gialinhnail.shop.enity.Category;
import com.gialinhnail.shop.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(value = "/admin/category")
public class CategoryController {
    private static Logger LOGGER = LoggerFactory.getLogger(CategoryController.class.getSimpleName());

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public String list(Model model, @RequestParam( value = "page") int page) {
        if (page != 0){
            page = page - 1;
        }
        Page<Category> categories = categoryService.categories(page);
        model.addAttribute("pageCategory", categories);
//        return getPathView("list");
        return "admin/page/category/list";
    }

    @GetMapping("/create")
    public String create(Model model) {
        return getPathView("create");
    }

    @PostMapping("/create")
    public String store(Model model, Category category) {
        return getPathRedirect("");
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        return getPathRedirect("");
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") long id) {
        return "admin/page/category/create";
    }

    @PostMapping("/update")
    public String update(Model model, Category category) {
        return getPathRedirect("/" + category.getId());
    }


    private static String getPathView(String nameView) {
        return "admin/page/category/" + nameView;
    }

    private static String getPathRedirect(String path) {
        return "redirect:/admin/category" + path;
    }
}
