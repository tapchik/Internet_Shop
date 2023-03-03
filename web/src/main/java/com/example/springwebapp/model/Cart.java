package com.example.springwebapp.model;

import java.util.HashMap;

public class Cart extends HashMap<String, Integer> {

    public Cart() {
        super();
    }

    public void addToCart(String product_id) {
        if (!this.containsKey(product_id)) {
            this.put(product_id, 1);
        }
        else {
            this.put(product_id, this.get(product_id)+1);
        }
    }

    public void deleteOneFromCart(String product_id) {
        if (this.containsKey(product_id) && this.get(product_id) >= 1) {
            this.put(product_id, this.get(product_id) - 1);
            if (this.get(product_id).equals(0)) {
                this.remove(product_id);
            }
        }
    }

    public void deleteProductFromCart(String product_id) {
        if (this.containsKey(product_id))
            this.remove(product_id);
    }

    public void deleteAllFromCart() {
        this.clear();
    }

    public String countItemsOfProduct(String product_id) {
        if (this.containsKey(product_id))
            return this.get(product_id).toString();
        return "0";
    }

    public String countOfAllProducts() {
        Integer count = 0;
        for(Integer val: this.values()) {
            count += val;
        }
        return count.toString();
    }

    public HashMap<String, Integer> getContent() {
        return this;
    }
}
