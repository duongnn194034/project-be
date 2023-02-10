package com.example.ecommerce.Dto.Checkout;

public class CheckoutItemDto {

    private String name;
    private int  quantity;
    private double price;
    private long productId;
    private int userId;

    public CheckoutItemDto() {}

    public CheckoutItemDto(String name, int quantity, double price, long productId, int userId) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.productId = productId;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice(){return price;}

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long id) {
        this.productId = id;
    }



}
