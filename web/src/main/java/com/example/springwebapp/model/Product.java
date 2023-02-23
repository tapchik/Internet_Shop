package com.example.springwebapp.model;

import java.util.Date;

public class Product {

    private String image_path;
    private String description;
    private Integer price;
    private Date availableFrom;

    public Product(final String image_path, final String description, final Integer price, final Date availableFrom) {
        this.image_path = image_path;
        this.description = description;
        this.price = price;
        this.availableFrom = availableFrom;
    }

    public String getImage_path() {
        return this.image_path;
    }

    public void setImage_path(final String availableFrom) {
        this.image_path = image_path;
    }

    public Date getAvailableFrom() {
        return this.availableFrom;
    }

    public void setAvailableFrom(final Date availableFrom) {
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
