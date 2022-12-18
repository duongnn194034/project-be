package com.example.ecommerce.Dto.Product;

import com.example.ecommerce.Model.Product;

import javax.validation.constraints.NotNull;

public class ProductDto {

    private Long id;
    private @NotNull String name;
    private @NotNull String imageURL;
    private @NotNull int price;
    private @NotNull String description;
    private @NotNull Long brandId;

    public ProductDto(Product product) {
        this.setId(product.getId());
        this.setName(product.getProductName());
        this.setImageURL(product.getImageUrl());
        this.setDescription(product.getProductDescription());
        this.setPrice(product.getPrice());
        this.setBrandId(product.getBrand().getId());
    }

    public ProductDto(@NotNull String name, @NotNull String imageURL, @NotNull int price, @NotNull String description, @NotNull Long categoryId) {
        this.name = name;
        this.imageURL = imageURL;
        this.price = price;
        this.description = description;
        this.brandId = categoryId;
    }

    public ProductDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long categoryId) {
        this.brandId = brandId;
    }
}
