package com.slowv.fruit.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import java.util.HashMap;

@Getter
@Setter
@RedisHash
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
