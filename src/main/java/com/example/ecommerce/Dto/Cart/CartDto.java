package com.example.ecommerce.Dto.Cart;

import com.example.ecommerce.Model.CartItem;

import java.util.List;

public class CartDto {
    private List<CartItem> cartItems;
    private double totalCost;

    public CartDto(List<CartItem> cartItemList, double totalCost) {
        this.cartItems = cartItemList;
        this.totalCost = totalCost;
    }

    public List<CartItem> getcartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItemDtoList) {
        this.cartItems = cartItemDtoList;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }
}
