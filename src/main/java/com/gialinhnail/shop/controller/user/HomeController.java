package com.gialinhnail.shop.controller.user;

import com.gialinhnail.shop.enity.Collection;
import com.gialinhnail.shop.service.CategoryService;
import com.gialinhnail.shop.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/")
public class HomeController {

    @Autowired
    CategoryService categoryService;
    @Autowired
    CollectionService collectionService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("categories", categoryService.findByStatus(1));
        List<Collection> collections =  collectionService.findAllNoPage();
        model.addAttribute("collections",collections);
        model.addAttribute("collectionNew", collectionService.findFirstByStatusAndOrderByCreatedAtDesc(1));
        return "user/index";
    }

    @GetMapping(value = "/products")
    public String products(Model model) {
        model.addAttribute("categories", categoryService.findByStatus(1));
        return "products";
    }
}
