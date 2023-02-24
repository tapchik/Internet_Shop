package com.example.springwebapp.repository;

import com.example.springwebapp.model.Cart;

import javax.servlet.http.HttpSession;

import java.util.HashMap;

public class CartRepository {

    HashMap <HttpSession, Cart> carts;

    public CartRepository () {
        carts = new HashMap<HttpSession, Cart>();
    }

    public void addProductToCart(HttpSession session, String product_id) {
        carts.get(session).addToCart(product_id);
    }

    public Cart getCart(HttpSession session) {
        return carts.get(session);
    }

    public int countItemsInCart (HttpSession session) {
        return this.getCart(session).countOfAllProducts();
    }
}
