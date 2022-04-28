package com.slowv.fruit.web.rest.user;

import com.slowv.fruit.domain.Cart;
import com.slowv.fruit.domain.CartItem;
import com.slowv.fruit.domain.rest.Response;
import com.slowv.fruit.service.impl.CategoryServiceImpl;
import com.slowv.fruit.service.impl.ProductServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/cart")
public class ShoppingCartController {

    private final CategoryServiceImpl categoryServiceImpl;

    private final ProductServiceImpl productServiceImpl;

    @GetMapping
    public String checkout(Model model) {
        model.addAttribute("categories", categoryServiceImpl.findByStatus(1));
        return "user/checkout";
    }

    @PostMapping("/plus/{id}")
    @ResponseBody
    public Response<Object> addProductToCart(HttpSession session, @PathVariable("id") long id) {
////        HttpSession session = request.getSession();
//        ProductDto productExist = productServiceImpl.findById(id);
//        if (productExist == null) {
//            throw new EntityNotFoundException();
//        }
//
//        HashMap<Long, CartItem> cartItems;
//        CartItem cartItem;
//        Cart cart;
//        double totalPrice;
//        if (null != session.getAttribute("cart")) {
//            cart = (Cart) session.getAttribute("cart");
//            totalPrice = cart.getTotalPrice();
//            cartItems = cart.getCartItems();
//            if (cartItems.containsKey(productExist.getId())) {
//                cartItem = cartItems.get(productExist.getId());
//                int quantity = cartItem.getQuantity();
//                cartItem.setQuantity(quantity + 1);
//                totalPrice += cartItem.getProduct().getUnitPrice();
//            }
//            cart.setTotalPrice(totalPrice);
//        } else {
//            cartItems = new HashMap<>();
//            cartItem = new CartItem(productExist, 1);
//            totalPrice = productExist.getUnitPrice() * cartItem.getQuantity();
//            cartItems.put(productExist.getId(), cartItem);
//            cart = new Cart(cartItems, totalPrice);
//            session.setAttribute("cart", cart);
//        }
//        return Response.ok(cart);
        return null;
    }

    @ResponseBody
    @PostMapping(value = "/remove/{id}")
    public Response<Object> removeProductFromCart(HttpSession session, @PathVariable("id") long id) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (null == cart) {
            throw new EntityNotFoundException();
        }
        HashMap<Long, CartItem> map = cart.getCartItems();
        if (!map.containsKey(id)){
            throw new EntityNotFoundException();
        }
        CartItem cartItem =  map.get(id);
        double totalPrice = cart.getTotalPrice();
        cart.setTotalPrice(totalPrice - (cartItem.getProduct().getUnitPrice() * cartItem.getQuantity()));
        return Response.ok(cart);
    }

}
