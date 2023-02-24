package com.example.springwebapp.model;

import java.util.HashMap;

public class Cart {
    public HashMap<String, Integer> cart_storage;

    public Cart() {
        cart_storage = new HashMap<String, Integer>();
    }

    public void addToCart(String product_id) {
        if (!cart_storage.containsKey(product_id)) {
            cart_storage.put(product_id, 1);
        }
        else {
            cart_storage.put(product_id, cart_storage.get(product_id)+1);
        }
    }

    public void deleteOneFromCart(String product_id) {
        cart_storage.put(product_id, cart_storage.get(product_id)-1);
        if (cart_storage.get(product_id).equals(0)) {
            cart_storage.remove(product_id);
        }
    }

    public void deleteAllFromCart() {
        cart_storage.clear();
    }

    public int countOfAllProducts() {
        Integer count = 0;
        for(Integer val: cart_storage.values()) {
            count += val;
        }
        return count;
    }

    public HashMap<String, Integer> getContent() {
        return this.cart_storage;
    }
}
