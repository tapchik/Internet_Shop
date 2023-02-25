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
        a_box.add(new Product("https://i.ebayimg.com/00/s/MTYwMFgxNjAw/z/RkkAAOSwtspdTBqx/$_57.JPG?set_id=8800005007","Apple iPhone 10", 41499, new Date(), "iph010"));
        a_box.add(new Product("https://cdn0.youla.io/files/images/360_360/63/31/63317929e0929665ca7d1f29-1.jpg","Apple iPhone 11", 49999, new Date(), "iph011"));
        a_box.add(new Product("https://cdn1.ozone.ru/s3/multimedia-2/6416880254.jpg","Apple iPhone 12", 54999, new Date(), "iph012"));
        a_box.add(new Product("https://static.onlinetrade.ru/img/items/b/mikrovolnovaya_pech_solo_daewoo_kor_6lbrc_2.jpg","Microwave", 15299, new Date(), "wave01"));
        a_box.add(new Product("https://avatars.mds.yandex.net/get-marketpic/988047/picf4f8b98517e27cd9407013305336e11d/orig","Samsung Galaxy S22", 58999, new Date(),"sam22"));
        a_box.add(new Product("https://cdn1.ozone.ru/s3/multimedia-u/6389646822.jpg","Samsung Galaxy S22 Ultra", 65999, new Date(), "sam22u"));
        return a_box;
    }

    public ArrayList<Product> getProducts(String filter, String sort_by) {
        ArrayList<Product> products = new ArrayList<Product>();
        if (filter!=null) {
            for (Product product: this.storage) {
                if (product.getDescription().toLowerCase().contains(filter.toLowerCase())) {
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

    public Product getProduct(String product_id) {
        for (Product product : this.storage) {
            if (product.getId() == product_id) {
                return product;
            }
        }
        return null;
    }

}