package com.example.ecommerce.Dto.Product;

import com.example.ecommerce.Model.Product;

import javax.validation.constraints.NotNull;

public class ProductDto {

    private long id;
    private @NotNull String name;
    private @NotNull String imageURL;
    private @NotNull int price;
    private @NotNull String description;
    private @NotNull long categoryId;


    public ProductDto() {

    }
    public ProductDto(Product product) {
        this.setId(product.getId());
        this.setName(product.getProductName());
        this.setImageURL(product.getImageUrl());
        this.setDescription(product.getProductDescription());
        this.setPrice(product.getPrice());
        this.setCategoryId(product.getCategory().getId());
    }

    public ProductDto(@NotNull String name, @NotNull String imageURL, @NotNull int price, @NotNull String description, @NotNull long categoryId) {
        this.name = name;
        this.imageURL = imageURL;
        this.price = price;
        this.description = description;
        this.categoryId = categoryId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }
}
