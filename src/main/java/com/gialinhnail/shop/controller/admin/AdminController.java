package com.gialinhnail.shop.controller.admin;

import com.gialinhnail.shop.enity.Category;
import com.gialinhnail.shop.enity.Collection;
import com.gialinhnail.shop.service.CategoryService;
import com.gialinhnail.shop.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    CollectionService collectionService;

    @GetMapping
    public String index() {
        return "admin/page/index";
    }
}
