package com.example.springwebapp.model;

import java.util.ArrayList;
import java.util.Date;

public class Storage {

    private ArrayList<Product> storage;

    public Storage() {
        this.storage = FillStorage();
    }

    private ArrayList<Product> FillStorage() {
        ArrayList<Product> a_box = new ArrayList<Product>();
        a_box.add(new Product("iPhone 10", 41499, new Date()));
        a_box.add(new Product("iPhone 11", 49999, new Date()));
        a_box.add(new Product("iPhone 12", 54999, new Date()));
        a_box.add(new Product("Microwave", 13499, new Date())); //"12-11-2022"
        a_box.add(new Product("Samsung S22", 59999, new Date()));
        a_box.add(new Product("Samsung S22 Ultra", 65999, new Date()));
        return a_box;
    }

    public ArrayList<Product> getProductsUnsorted() {
        return this.storage;
    }

    public ArrayList<Product> getProductsSortedByDescriptionAsc() {
        this.storage.sort((o1, o2) -> o1.getDescription().compareTo(o2.getDescription()));
        return this.storage;
    }

    public ArrayList<Product> getProductsSortedByDescriptionDesc() {
        this.storage.sort((o1, o2) -> o2.getDescription().compareTo(o1.getDescription()));
        return this.storage;
    }

    public ArrayList<Product> getProductsSortedByPriceAsc() {
        this.storage.sort((o1, o2) -> o1.getPrice().compareTo(o2.getPrice()));
        return this.storage;
    }

    public ArrayList<Product> getProductsSortedByPriceDesc() {
        this.storage.sort((o1, o2) -> o2.getPrice().compareTo(o1.getPrice()));
        return this.storage;
    }

}