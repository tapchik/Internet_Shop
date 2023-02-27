package com.example.springwebapp.model;

import java.util.Date;

public class Product {

    private String image_path;
    private String description;
    private Integer price;
    private String availableFrom;
    private String id;

    public Product(final String image_path, final String description, final Integer price, final String availableFrom, final String id) {
        this.image_path = image_path;
        this.description = description;
        this.price = price;
        this.availableFrom = availableFrom;
        this.id = id;
    }

    public Product(final String description, final Integer price, final String availableFrom, final String id) {
        this.image_path = "https://cdn1.ozone.ru/s3/multimedia-2/6416880254.jpg";
        this.description = description;
        this.price = price;
        this.availableFrom = availableFrom;
        this.id = id;
    }

    public String getBeautifulPrice(){
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

    public String getImage_path() {
        return this.image_path;
    }

    public void setImage_path(final String availableFrom) {
        this.image_path = image_path;
    }

    public String getId() { return this.id; }

    public String getAvailableFrom() {
        return this.availableFrom;
    }

    public void setAvailableFrom(final String availableFrom) {
        this.availableFrom = availableFrom;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return this.price;
    }

    public void setPrice(final Integer price) {
        this.price = price;
    }

}