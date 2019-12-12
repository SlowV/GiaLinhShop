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

    @GetMapping("/seeder")
    @ResponseBody
    public String seeder() {
        List<Category> categories = new ArrayList<>(
                Arrays.asList(
                        new Category("Dụng cụ Nails", "Dùng để làm Nail", "http://viendaotaothammy.com/wp-content/uploads/2016/10/do-nghe-lam-nail-can-thiet-de-mo-tiem-nail-1.jpg"),
                        new Category("Chăm sóc Nails", "Dịch cụ chăm sóc Nail", "https://photo-2-baomoi.zadn.vn/w1000_r1/2019_01_09_322_29290888/1ec026dc9d9d74c32d8c.jpg"),
                        new Category("Makeup", "Dịch cụ Trang điểm", "https://media.ex-cdn.com/EXP/media.phunutoday.vn/files/content/2019/04/21/ve-dep-sieu-thuc-cua-nu-hoang-makeup-han-quoc-gay-sot-toan-the-gioi-6-1555484784-605-width800height790-0859.jpg"),
                        new Category("Mỹ phẩm", "Mỹ phẩm xách tay", "https://media.healthplus.vn/thumb_x650x382/Images/Uploaded/Share/2018/02/08/10-bi-mat-ma-nganh-cong-nghiep-my-pham-my-khong-muon-tiet-lo-voi-ban11518078340.jpg")
                )
        );
        for (Category category : categories) {
            categoryService.save(category);
        }

        List<Collection> collections = new ArrayList<>(
                Arrays.asList(
                        new Collection("Mùa Xuân", "Dùng để làm Nail", "http://viendaotaothammy.com/wp-content/uploads/2016/10/do-nghe-lam-nail-can-thiet-de-mo-tiem-nail-1.jpg"),
                        new Collection("Mùa Hè", "Dịch cụ chăm sóc Nail", "https://photo-2-baomoi.zadn.vn/w1000_r1/2019_01_09_322_29290888/1ec026dc9d9d74c32d8c.jpg"),
                        new Collection("Mùa Thu", "Dịch cụ Trang điểm", "https://media.ex-cdn.com/EXP/media.phunutoday.vn/files/content/2019/04/21/ve-dep-sieu-thuc-cua-nu-hoang-makeup-han-quoc-gay-sot-toan-the-gioi-6-1555484784-605-width800height790-0859.jpg"),
                        new Collection("Mùa Đông", "Mỹ phẩm xách tay", "https://media.healthplus.vn/thumb_x650x382/Images/Uploaded/Share/2018/02/08/10-bi-mat-ma-nganh-cong-nghiep-my-pham-my-khong-muon-tiet-lo-voi-ban11518078340.jpg")
                )
        );
        for (Collection collection : collections) {
            collectionService.save(collection);
        }
        return "OK";
    }
}
