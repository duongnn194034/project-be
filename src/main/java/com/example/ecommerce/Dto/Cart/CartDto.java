package com.example.ecommerce.Dto.Cart;

import com.example.ecommerce.Model.CartItem;

import java.util.List;

public class CartDto {
    private List<CartItemDto> cartItems;
    private int totalCost;

    public CartDto(List<CartItemDto> cartItemList, int totalCost) {
        this.cartItems = cartItemList;
        this.totalCost = totalCost;
    }

    public List<CartItemDto> getcartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemDto> cartItemDtoList) {
        this.cartItems = cartItemDtoList;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }
}
