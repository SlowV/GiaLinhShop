package com.gialinhnail.shop.controller.admin;

import com.gialinhnail.shop.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/admin/collection")
public class CollectionController {
    @Autowired
    CollectionService collectionService;
    @GetMapping
    public String list(Model model, @RequestParam("page") int page){
        model.addAttribute("pageCollection", collectionService.collections(page));
        return "admin/page/collection/list";
    }
}
