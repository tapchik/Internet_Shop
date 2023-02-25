package com.example.springwebapp.model;

public class Order {
    public final String id;
    public final String order_time;
    public final String order_city;

    public Order(final String id, final String order_time, final String order_city) {
        this.id= id;
        this.order_time = order_time;
        this.order_city = order_city;
    }
}
