package com.example.springwebapp.repository;

import com.example.springwebapp.model.Cart;
import com.example.springwebapp.model.Product;

import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CartRepository {

    HashMap <HttpSession, Cart> carts;

    public CartRepository() {
        carts = new HashMap<HttpSession, Cart>();
    }

    public void plusOneItemToCart(HttpSession session, String product_id) {
        this.addSessionIfDoesntExist(session);
        carts.get(session).addToCart(product_id);
    }

    public String amountOf(HttpSession session, String product_id) {
        this.addSessionIfDoesntExist(session);
        return carts.get(session).countItemsOfProduct(product_id);
    }

    public void minusOneItemFromCart(HttpSession session, String product_id) {
        this.addSessionIfDoesntExist(session);
        carts.get(session).deleteOneFromCart(product_id);
    }

    public void removeProductFromCart(HttpSession session, String product_id) {
        this.addSessionIfDoesntExist(session);
        carts.get(session).deleteProductFromCart(product_id);
    }

    public int countItemsInCart(HttpSession session) {
        this.addSessionIfDoesntExist(session);
        return this.getCart(session).countOfAllProducts();
    }

    public ArrayList<HashMap<String, String>> getCartItems(HttpSession session, ProductRepository productRepository) {
        Cart cart = this.getCart(session);
        ArrayList<HashMap<String, String>> cart_items = new ArrayList<>();
        for (String product_id: cart.keySet()) {
            Product product = productRepository.getProduct(product_id);
            HashMap<String, String> cart_item = new HashMap<>();
            cart_item.put("product_id", product_id);
            cart_item.put("quantity", cart.get(product_id).toString());
            cart_item.put("image_path", product.getImage_path());
            cart_item.put("description", product.getDescription());
            cart_item.put("price", product.getBeautifulPrice());
            cart_item.put("availableFrom", product.getAvailableFrom().toString());
            cart_items.add(cart_item);
        }
        return cart_items;
    }

    public Cart getCart(HttpSession session) {
        this.addSessionIfDoesntExist(session);
        return carts.get(session);
    }

    public String getBeautifulOrderPrice(HttpSession session, ProductRepository productRepository) {
        Integer order_price = 0;
        for (String product_id: getCart(session).keySet()) {
            order_price += productRepository.getProduct(product_id).getPrice()*getCart(session).get(product_id);
        }
        return orderPriceToBeautiful(order_price);
    }

    private String orderPriceToBeautiful(Integer price) {
        String new_price = "";
        for (Integer i = price.toString().length()-1; i >= 0; i--) {
            new_price = price.toString().charAt(i) + new_price;
            Integer len = (new_price.replace(",","").length()%3);
            if (len.equals(0) && price.toString().length()!=new_price.replace(",","").length()) {
                new_price = "," + new_price;
            }
        }
        return new_price + "₽";
    }

    private void addSessionIfDoesntExist(HttpSession session) {
        if (!this.carts.containsKey(session))
            carts.put(session, new Cart());
    }
}
