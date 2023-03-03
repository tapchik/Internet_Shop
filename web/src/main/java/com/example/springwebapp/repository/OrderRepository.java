package com.example.springwebapp.repository;

import com.example.springwebapp.model.Cart;
import com.example.springwebapp.model.Order;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class OrderRepository {

    ArrayList<Order> orderRepository;

    public OrderRepository() {
        orderRepository = new ArrayList<Order>();
    }

    public ArrayList<Order> getOrderRepository() {
        return this.orderRepository;
    }

    public boolean makeOrder(String id, String order_time, String order_city) {
        try {
            this.orderRepository.add(new Order(id, order_time, order_city));
        }
        catch (Exception err) {
            return false;
        }
        return true;
    }
}
