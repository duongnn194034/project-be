package com.example.ecommerce.Dto.Cart;

import com.sun.istack.NotNull;

public class AddToCartDto {
    private long id;
    private @NotNull long productId;
    private @NotNull Integer quantity;

    public AddToCartDto() {
    }



    @Override
    public String toString() {
        return "CartDto{" +
                "id=" + id +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ",";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
