package com.slowv.fruit.web.rest.admin;

import com.slowv.fruit.service.impl.CollectionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/admin/collection")
public class CollectionResource {
    @Autowired
    CollectionServiceImpl collectionServiceImpl;
    @GetMapping
    public String list(Model model, @RequestParam("page") int page, @RequestParam("size") int size){
        model.addAttribute("pageCollection", collectionServiceImpl.collections(page, size));
        return "admin/page/collection/list";
    }
}
