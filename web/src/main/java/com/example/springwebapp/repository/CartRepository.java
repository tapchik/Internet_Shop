package com.example.springwebapp.repository;

import com.example.springwebapp.model.Cart;

import javax.servlet.http.HttpSession;

import java.util.HashMap;

public class CartRepository {

    HashMap <HttpSession, Cart> carts;

    public CartRepository () {
        carts = new HashMap<HttpSession, Cart>();
    }

    public void addCartCheck(HttpSession session) {
        if (!this.carts.containsKey(session))
            carts.put(session, new Cart());
    }

    public void addProductToCart(HttpSession session, String product_id) {
        this.addCartCheck(session);
        carts.get(session).addToCart(product_id);
    }

    public Cart getCart(HttpSession session) {
        this.addCartCheck(session);
        return carts.get(session);
    }

    public int countItemsInCart (HttpSession session) {
        this.addCartCheck(session);
        return this.getCart(session).countOfAllProducts();
    }
}
