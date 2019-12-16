package com.gialinhnail.shop.enity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;

@Getter
@Setter
public class Cart {
    private HashMap<Long, CartItem> cartItems;
    private double totalPrice;

    public Cart(HashMap<Long, CartItem> cartItems, double totalPrice) {
        this.cartItems = cartItems;
        this.totalPrice = totalPrice;
    }

    public Cart() {
    }
}
