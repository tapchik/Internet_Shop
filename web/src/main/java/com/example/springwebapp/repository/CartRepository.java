package com.example.springwebapp.repository;
import java.util.HashMap;

public class CartRepository {
    HashMap <String, Integer> cart_storage;

    public CartRepository() {
        cart_storage = new HashMap<String, Integer>();
    }

    public void AddToCart(String id) {
        if (!cart_storage.containsKey(id)) {
            cart_storage.put(id, 1);
        }
        else {
            cart_storage.put(id, cart_storage.get(id)+1);
        }
    }

    public void DeleteOneFromCart(String id) {
        cart_storage.put(id, cart_storage.get(id)-1);
        if (cart_storage.get(id).equals(0)) {
            cart_storage.remove(id);
        }
    }

    public void DeleteAllFromCart() {
        cart_storage.clear();
    }
}
