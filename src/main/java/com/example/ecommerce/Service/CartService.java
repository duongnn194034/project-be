package com.example.ecommerce.Service;

import com.example.ecommerce.Dto.Cart.AddToCartDto;
import com.example.ecommerce.Dto.Cart.CartDto;
import com.example.ecommerce.Dto.Cart.CartItemDto;
import com.example.ecommerce.Exception.CartItemNotExistException;
import com.example.ecommerce.Model.CartItem;
import com.example.ecommerce.Model.CartSession;
import com.example.ecommerce.Model.Product;
import com.example.ecommerce.Model.User;
import com.example.ecommerce.Repository.CartSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CartService {

    @Autowired
    private CartSessionRepository cartRepository;

    public CartService(){}

    public CartService(CartSessionRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public void addToCart(AddToCartDto addToCartDto, List<CartItem> items, User user){
        CartSession cart = new CartSession(items, addToCartDto.getQuantity(), user);
        cartRepository.save(cart);
    }


    public CartDto listCartItems(User user) {
        List<CartSession> cartList = cartRepository.findAllByUserOrderByCreatedDateDesc(user);
        List<CartItemDto> cartItems = new ArrayList<>();
        for (CartSession cart:cartList){
            CartItemDto cartItemDto = getDtoFromCart(cart);
            cartItems.add(cartItemDto);
        }
        double totalCost = 0;
        for (CartItemDto cartItemDto :cartItems){
            totalCost += (cartItemDto.getProduct().getPrice()* cartItemDto.getQuantity());
        }
        return new CartDto(cartItems,totalCost);
    }


    public static CartItemDto getDtoFromCart(CartSession cart) {
        return new CartItemDto(cart);
    }


    public void updateCartItem(AddToCartDto cartDto, User user, Product product){
        CartSession cart = cartRepository.getReferenceById(cartDto.getId());
        cart.setTotalAmount(cartDto.getQuantity());
        cart.setCreatedAt((java.sql.Date) new Date());
        cartRepository.save(cart);
    }

    public void deleteCartItem(Long id, int userId) throws CartItemNotExistException {
        if (!cartRepository.existsById(id))
            throw new CartItemNotExistException("Cart id is invalid : " + id);
        cartRepository.deleteById(id);
    }

    public void deleteCartItems(int userId) {
        cartRepository.deleteAll();
    }


    public void deleteUserCartItems(User user) {
        cartRepository.deleteByUser(user);
    }
}


