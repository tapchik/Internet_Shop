package com.example.springwebapp.repository;

import com.example.springwebapp.model.Cart;

import javax.servlet.http.HttpSession;

public class CartRepository {

    //TODO по ключу session нужно хранить Cart

    public void addProductToCart(HttpSession session, String product_id) {
        //TODO если product_id добавляется впервые - его количество = 1
        // если product_id уже есть в cart - увеличить его количество в корзине += 1
    }

    public Cart getCart(HttpSession session) {
        //TODO возвращать сущность Cart по указанному session
        return new Cart();
    }
}
