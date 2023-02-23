package com.example.springwebapp.repository;

import com.example.springwebapp.model.Product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

public class ProductRepository {

    private ArrayList<Product> storage;

    public ProductRepository() {
        this.storage = FillStorage();
    }

    private ArrayList<Product> FillStorage() {
        ArrayList<Product> a_box = new ArrayList<Product>();
        a_box.add(new Product("https://cdn1.ozone.ru/s3/multimedia-2/6416880254.jpg","iPhone 10", 41499, new Date()));
        a_box.add(new Product("https://cdn1.ozone.ru/s3/multimedia-2/6416880254.jpg","iPhone 11", 49999, new Date()));
        a_box.add(new Product("https://cdn1.ozone.ru/s3/multimedia-2/6416880254.jpg","iPhone 12", 54999, new Date()));
        a_box.add(new Product("https://cdn1.ozone.ru/s3/multimedia-2/6416880254.jpg","Microwave", 13499, new Date()));
        a_box.add(new Product("https://cdn1.ozone.ru/s3/multimedia-2/6416880254.jpg","Samsung S22", 59999, new Date()));
        a_box.add(new Product("https://cdn1.ozone.ru/s3/multimedia-2/6416880254.jpg","Samsung S22 Ultra", 65999, new Date()));
        return a_box;
    }

    public ArrayList<Product> getProducts(String filter, String sort_by) {
        ArrayList<Product> products = new ArrayList<Product>();
        if (filter!=null) {
            for (Product product: this.storage) {
                if (product.getDescription().contains(filter)) {
                    products.add(product);
                }
            }
        }
        else {
            products = this.storage;
        }
        if (sort_by.equals("description-asc")) {
            products.sort((o1, o2) -> o1.getDescription().compareTo(o2.getDescription()));
        }
        else if (sort_by.equals("description-desc")) {
            products.sort((o1, o2) -> o2.getDescription().compareTo(o1.getDescription()));
        }
        else if (sort_by.equals("price-asc")) {
            products.sort((o1, o2) -> o1.getPrice().compareTo(o2.getPrice()));
        }
        else if (sort_by.equals("price-desc")) {
            products.sort((o1, o2) -> o2.getPrice().compareTo(o1.getPrice()));
        }
        return products;
    }
}
