package com.slowv.fruit.web.rest.seeder;
import com.slowv.fruit.domain.Category;
import com.slowv.fruit.domain.Collection;
import com.slowv.fruit.domain.Product;
import com.slowv.fruit.service.impl.CategoryServiceImpl;
import com.slowv.fruit.service.impl.CollectionServiceImpl;
import com.slowv.fruit.service.impl.ProductServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(value = "/seeder")
public class SeederController {
    @Autowired
    CategoryServiceImpl categoryServiceImpl;
    @Autowired
    CollectionServiceImpl collectionServiceImpl;
    @Autowired
    ProductServiceImpl productServiceImpl;

    private static Logger LOGGER = LoggerFactory.getLogger(SeederController.class.getSimpleName());

    @GetMapping
    public String seeder() {
        List<Category> existCategory = categoryServiceImpl.findAllNoStatus();
        List<Category> categories = new ArrayList<>(
            Arrays.asList(
                new Category("Dụng cụ Nails", "Dùng để làm Nail", "http://viendaotaothammy.com/wp-content/uploads/2016/10/do-nghe-lam-nail-can-thiet-de-mo-tiem-nail-1.jpg"),
                new Category("Nails", "Dịch cụ chăm sóc Nail", "https://photo-2-baomoi.zadn.vn/w1000_r1/2019_01_09_322_29290888/1ec026dc9d9d74c32d8c.jpg"),
                new Category("Makeup", "Dịch cụ Trang điểm", "https://media.ex-cdn.com/EXP/media.phunutoday.vn/files/content/2019/04/21/ve-dep-sieu-thuc-cua-nu-hoang-makeup-han-quoc-gay-sot-toan-the-gioi-6-1555484784-605-width800height790-0859.jpg"),
                new Category("Mỹ phẩm", "Mỹ phẩm xách tay", "https://media.healthplus.vn/thumb_x650x382/Images/Uploaded/Share/2018/02/08/10-bi-mat-ma-nganh-cong-nghiep-my-pham-my-khong-muon-tiet-lo-voi-ban11518078340.jpg")
            )
        );
        if (existCategory != null && existCategory.size() > 0) {
            LOGGER.info("Category is has data!");
        } else {
            categoryServiceImpl.saveAll(categories);
            categoryServiceImpl.saveAll(new ArrayList<>(
                    Arrays.asList(
                            new Category("Máy tỉa móng", "Dùng để tỉa móng", "https://scontent.fhan5-2.fna.fbcdn.net/v/t1.0-9/35144095_261311484612716_3780021221383471104_n.jpg?_nc_cat=110&_nc_ohc=lbMbon4o3NUAQnR6FAdldbQqiIoDNL98qSKmguiz_TQtdqWb-v2wkv-gA&_nc_ht=scontent.fhan5-2.fna&oh=4e69e82ee579137f85a1c4fe330ebf26&oe=5E7C80A5", categoryServiceImpl.findById(1)),
                            new Category("Hoa bột", "Đắp móng", "https://scontent.fhan5-1.fna.fbcdn.net/v/t1.0-9/s960x960/69244702_532358144174714_2531345525446279168_o.jpg?_nc_cat=109&_nc_ohc=S_VoEQYc9RkAQkO3haFFXeyvqhOKLJHqDyGVE7J_lqiPvwSQcdsjMHwXQ&_nc_ht=scontent.fhan5-1.fna&oh=faa5f4298cc89343e8ac40560213626f&oe=5E6AAE4F", categoryServiceImpl.findById(2)),
                            new Category("Makeup Tây", "Tone Us", "https://phunublog.vn/wp-content/uploads/2019/08/make-up-la-gi-01.jpg", categoryServiceImpl.findById(3)),
                            new Category("Bảng mắt", "Bảng mắt", "https://scontent.fhan5-7.fna.fbcdn.net/v/t1.0-9/27857797_1647345675349443_811526293224089405_n.jpg?_nc_cat=103&_nc_ohc=xRHgisiygs4AQmfZ-UKx__0gVxkAUYy2j5i7-vUua8TD2ZkV7IbEMrHRg&_nc_ht=scontent.fhan5-7.fna&oh=7ef46a5be8f894ea90f1dc0bbf946e40&oe=5EB3A05B", categoryServiceImpl.findById(4)),
                            new Category("Hoa bột Đỏ", "Hoa Bột", "https://scontent-hkg3-1.xx.fbcdn.net/v/t1.0-9/s960x960/75462355_593977598012768_2785297230737702912_o.jpg?_nc_cat=108&_nc_ohc=-tA3GmWMun4AQnB4DmW0z3qKaZ2HOfkfhkc6zp2GAq0LrvXX_wp5_rtew&_nc_ht=scontent-hkg3-1.xx&oh=cc03c783955c646594f4bda8fced7841&oe=5EB096B5", categoryServiceImpl.findById(2))
                        )
                    )
            );
        }

        List<Collection> existCollection = collectionServiceImpl.findAllNoPage();
        List<Collection> collections = new ArrayList<>(
            Arrays.asList(
                new Collection("Mùa Xuân", "Mùa xuân", "http://trainghiemvanhoa.vn/images/anh_1.png", true),
                new Collection("Mùa Hè", "Mùa hè", "https://photo-2-baomoi.zadn.vn/w1000_r1/2019_01_09_322_29290888/1ec026dc9d9d74c32d8c.jpg",  true),
                new Collection("Mùa Thu", "Mùa thu", "https://media.ex-cdn.com/EXP/media.phunutoday.vn/files/content/2019/04/21/ve-dep-sieu-thuc-cua-nu-hoang-makeup-han-quoc-gay-sot-toan-the-gioi-6-1555484784-605-width800height790-0859.jpg", true),
                new Collection("Mùa Đông", "Mùa đông", "https://media.healthplus.vn/thumb_x650x382/Images/Uploaded/Share/2018/02/08/10-bi-mat-ma-nganh-cong-nghiep-my-pham-my-khong-muon-tiet-lo-voi-ban11518078340.jpg", true)
            )
        );
        if (existCollection != null && existCollection.size() > 0) {
            LOGGER.info("Collection is has data!");
        } else {
            collectionServiceImpl.saveAll(collections);
            collectionServiceImpl.saveAll(new ArrayList<>(
                    Arrays.asList(
                            new Collection("Makeup", "Makeup", "http://trainghiemvanhoa.vn/images/anh_1.png", false, 1),
                            new Collection("Bảng mắt", "Bảng mắt", "https://photo-2-baomoi.zadn.vn/w1000_r1/2019_01_09_322_29290888/1ec026dc9d9d74c32d8c.jpg",  false, 1),
                            new Collection("Móng úp", "Móng úp", "https://media.ex-cdn.com/EXP/media.phunutoday.vn/files/content/2019/04/21/ve-dep-sieu-thuc-cua-nu-hoang-makeup-han-quoc-gay-sot-toan-the-gioi-6-1555484784-605-width800height790-0859.jpg", false, 1),
                            new Collection("Keo", "Keo", "https://media.healthplus.vn/thumb_x650x382/Images/Uploaded/Share/2018/02/08/10-bi-mat-ma-nganh-cong-nghiep-my-pham-my-khong-muon-tiet-lo-voi-ban11518078340.jpg", false, 1)
                    )
            ));

        }

        List<Product> existProduct = productServiceImpl.findAllNoPage();
        List<Product> products = new ArrayList<>(
            Arrays.asList(
                new Product("SET SƠN ĐỎ CHANEL", 10000.0, "Set sơn đỏ hàng hiệu.",
                    "https://limnail.com/img/up-anh/anh-6354-72533971_2373721362841077_524600010046701568_n.jpg, https://limnail.com/img/up-anh//anh-7214-73084143_417501735632891_4799026569119006720_n.jpg, https://limnail.com/img/up-anh/anh-8355-72994085_694997197687353_2050994617830080512_n.jpg, https://limnail.com/img/up-anh/anh-3283-72942664_752826408522303_1045832306171838464_n.jpg" , 0, "set sơn đỏ sml", 50, categoryServiceImpl.findAllNoStatus().get(0), collectionServiceImpl.findAllNoPage().get(0)),
                new Product("MÁY HƠ GEL TÍCH ĐIỆN", 9000.0, "MÁY HƠ GEL TÍCH ĐIỆN",
                    "https://anatran.vn/image/cache/imgdata/dung-cu-nail/may-lam-nail/lamp_ans_02/may_ho_tich_dien_1-800x390.png, https://anatran.vn/image/cache/imgdata/dung-cu-nail/may-lam-nail/lamp_ans_02/8691888480_250008574.jpg, https://anatran.vn/image/cache/imgdata/dung-cu-nail/may-lam-nail/lamp_ans_02/8691897207_250008574.jpg, https://anatran.vn/image/cache/imgdata/dung-cu-nail/may-lam-nail/lamp_ans_02/8691906034_250008574.jpg" , 10, "MÁY HƠ GEL TÍCH ĐIỆN", 40, categoryServiceImpl.findAllNoStatus().get(1), collectionServiceImpl.findAllNoPage().get(1)),
                new Product("MÁY HÚT BỤI CÓ VÒI", 800000.0, "MÁY HÚT BỤI CÓ VÒI",
                    "https://anatran.vn/image/cache/imgdata/dung-cu-nail/may-lam-nail/may_hut/may_hut_co_voi_1-800x390.png, https://anatran.vn/image/cache/imgdata/dung-cu-nail/may-lam-nail/may_hut/9382734480_443469210.jpg, https://anatran.vn/image/cache/imgdata/dung-cu-nail/may-lam-nail/may_hut/9407494413_443469210.jpg, https://anatran.vn/image/cache/imgdata/dung-cu-nail/may-lam-nail/may_hut/9427394974_443469210.jpg" , 0, "MÁY HÚT BỤI CÓ VÒI",20, categoryServiceImpl.findAllNoStatus().get(2), collectionServiceImpl.findAllNoPage().get(2)),
                new Product("BỘ LIÊN KẾT SƠN GEL ANS", 800000.0, "BỘ LIÊN KẾT SƠN GEL ANS",
                    "https://anatran.vn/image/cache/imgdata/combo/bo_lien_ket_2-800x390.png, https://anatran.vn/image/cache/imgdata/combo/bo_lien_ket.png", 20, "BỘ LIÊN KẾT SƠN GEL ANS", 100, categoryServiceImpl.findAllNoStatus().get(3), collectionServiceImpl.findAllNoPage().get(3))
                )
        );
        if (existProduct != null && existProduct.size() > 0) {
            LOGGER.info("Product is has data!");
        } else {
            for (Product product : products) {
                productServiceImpl.save(product);
            }
        }
        return "redirect:/";
    }
}
