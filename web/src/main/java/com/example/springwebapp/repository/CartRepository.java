package com.example.springwebapp.repository;

import com.example.springwebapp.model.Cart;
import com.example.springwebapp.model.Product;

import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    public ArrayList<HashMap<String, String>> getCartItems(HttpSession session, ProductRepository productRepository) {
        Cart cart = this.getCart(session);
        ArrayList<HashMap<String, String>> cart_items = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : cart.entrySet()) {
            String product_id = entry.getKey();
            String quantity = entry.getValue().toString();
            HashMap<String, String> cart_item = new HashMap<>();
            Product product = productRepository.getProduct(product_id);
            cart_item.put("product_id", product_id);
            cart_item.put("quantity", quantity);
            cart_item.put("image_path", product.getImage_path());
            cart_item.put("description", product.getDescription());
            cart_item.put("price", product.getBeautifulPrice());
            cart_item.put("availableFrom", product.getAvailableFrom().toString());
            cart_items.add(cart_item);
        }
        return null;
    }

}
