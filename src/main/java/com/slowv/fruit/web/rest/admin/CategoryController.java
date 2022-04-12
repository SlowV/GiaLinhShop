package com.slowv.fruit.web.rest.admin;

import com.slowv.fruit.domain.Category;
import com.slowv.fruit.service.impl.CategoryServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@AllArgsConstructor
@Slf4j
@RequestMapping(value = "/admin/category")
public class CategoryController {

    private final CategoryServiceImpl categoryServiceImpl;

    @GetMapping
    public String list(Model model, @RequestParam( value = "page") int page, @RequestParam("size") int size) {
        if (page != 0){
            page = page - 1;
        }

        Page<Category> categories = categoryServiceImpl.categories(page, size);
        model.addAttribute("pageCategory", categories);
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
