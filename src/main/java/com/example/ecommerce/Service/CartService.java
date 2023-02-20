package com.example.ecommerce.Service;

import com.example.ecommerce.Dto.Cart.AddToCartDto;
import com.example.ecommerce.Dto.Cart.CartDto;
import com.example.ecommerce.Dto.Cart.CartItemDto;
import com.example.ecommerce.Exception.CartItemNotExistException;
import com.example.ecommerce.Model.CartItem;
import com.example.ecommerce.Model.Product;
import com.example.ecommerce.Model.User;
import com.example.ecommerce.Repository.CartItemRepository;
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
    private CartItemRepository cartRepository;

    public CartService(){}

    public CartService(CartItemRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public void addToCart(AddToCartDto addToCartDto, Product product, User user){
        CartItem cart = new CartItem(product, addToCartDto.getQuantity(), user);
        cartRepository.save(cart);
    }


    public CartDto listCartItems(User user) {
        List<CartItem> cartList = cartRepository.findAllByUserOrderByCreatedDateDesc(user);
        List<CartItemDto> cartItemDtoList = new ArrayList<>();
        int totalCost = 0;
        for (CartItem cartItem : cartList) {
            cartItemDtoList.add(new CartItemDto(cartItem));
            totalCost += (cartItem.getProduct().getPrice() * cartItem.getQuantity());
        }
        return new CartDto(cartItemDtoList, totalCost);
    }


    public void updateCartItem(AddToCartDto cartDto, User user, Product product){
        CartItem cart = cartRepository.getReferenceById(cartDto.getId());
        cart.setQuantity(cartDto.getQuantity());
        cart.setCreatedAt((java.sql.Date) new Date());
        cartRepository.save(cart);
    }

    public void deleteCartItem(long id, long userId) throws CartItemNotExistException {
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

    public int count(User user) {
        return cartRepository.countAll(user);
    }

    public void deleteCartItemsByProduct(Product product) {
        cartRepository.deleteAllByProduct(product);
    }
}


