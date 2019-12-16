package com.gialinhnail.shop.controller.user;

import com.gialinhnail.shop.enity.Cart;
import com.gialinhnail.shop.enity.CartItem;
import com.gialinhnail.shop.enity.Product;
import com.gialinhnail.shop.enity.rest.RESTResponse;
import com.gialinhnail.shop.service.CategoryService;
import com.gialinhnail.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
@RequestMapping(value = "/cart")
public class ShoppingCartController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

    @GetMapping
    public String checkout(Model model) {
        model.addAttribute("categories", categoryService.findByStatus(1));
        return "user/checkout";
    }

    @PostMapping("/add/{id}")
    @ResponseBody
    public ResponseEntity<Object> addProductToCart(HttpSession session, @PathVariable("id") long id) {
//        HttpSession session = request.getSession();
        Product productExist = productService.findById(id);
        HashMap<Long, CartItem> cartItems;
        CartItem cartItem;
        Cart cart;
        double totalPrice;
        if (productExist == null) {
            return new ResponseEntity<>(new RESTResponse.SimpleError().setCode(HttpStatus.NOT_FOUND.value()).setMessage("Not found!").build(), HttpStatus.NOT_FOUND);
        }
        if (null != session.getAttribute("cart")) {
            cart = (Cart) session.getAttribute("cart");
            totalPrice = cart.getTotalPrice();
            cartItems = cart.getCartItems();
            if (cartItems.containsKey(productExist.getId())) {
                cartItem = cartItems.get(productExist.getId());
                int quantity = cartItem.getQuantity();
                cartItem.setQuantity(quantity + 1);
                totalPrice += cartItem.getProduct().getUnitPrice();
            }
            cart.setTotalPrice(totalPrice);
        } else {
            cartItems = new HashMap<>();
            cartItem = new CartItem(productExist, 1);
            totalPrice = productExist.getUnitPrice() * cartItem.getQuantity();
            cartItems.put(productExist.getId(), cartItem);
            cart = new Cart(cartItems, totalPrice);
            session.setAttribute("cart", cart);
        }
        return new ResponseEntity<>(new RESTResponse.Success().setData(cart).setMessage("Add to cart success!").setStatus(HttpStatus.OK.value()).build(), HttpStatus.OK);
    }

    public ResponseEntity<String> removeProductFromCart() {
        return new ResponseEntity<>("Remove Success!", HttpStatus.OK);
    }

}
