package com.slowv.fruit.web.rest.admin;

import com.slowv.fruit.service.impl.CategoryServiceImpl;
import com.slowv.fruit.service.impl.CollectionServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/admin")
@AllArgsConstructor
public class AdminResource {
    private final CategoryServiceImpl categoryServiceImpl;
    private final CollectionServiceImpl collectionServiceImpl;

    @GetMapping
    public String index() {
        return "admin/page/index";
    }
}
