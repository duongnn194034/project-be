package com.example.ecommerce.Controller;

import com.example.ecommerce.Common.ApiResponse;
import com.example.ecommerce.Dto.Cart.AddToCartDto;
import com.example.ecommerce.Dto.Cart.CartDto;
import com.example.ecommerce.Exception.AuthenticationFailException;
import com.example.ecommerce.Exception.CartItemNotExistException;
import com.example.ecommerce.Exception.ProductNotExistException;
import com.example.ecommerce.Model.Product;
import com.example.ecommerce.Model.User;
import com.example.ecommerce.Service.AuthenticationService;
import com.example.ecommerce.Service.CartService;
import com.example.ecommerce.Service.ProductService;
import com.example.ecommerce.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToCart(@RequestBody AddToCartDto addToCartDto,
                                                 @RequestParam("token") String token) throws AuthenticationFailException, ProductNotExistException {
        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);
        Product product = productService.getProductById(addToCartDto.getProductId());
        cartService.addToCart(addToCartDto, product, user);
        return new ResponseEntity<>(new ApiResponse(true, "Added to cart"), HttpStatus.CREATED);

    }
    @GetMapping("/")
    public ResponseEntity<CartDto> getCartItems(@RequestParam("token") String token) throws AuthenticationFailException {
        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);
        CartDto cartDto = cartService.listCartItems(user);
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }
    @PutMapping("/update/{cartItemId}")
    public ResponseEntity<ApiResponse> updateCartItem(@RequestBody @Valid AddToCartDto cartDto,
                                                      @RequestParam("token") String token) throws AuthenticationFailException,ProductNotExistException {
        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);
        Product product = productService.getProductById(cartDto.getProductId());
        cartService.updateCartItem(cartDto, user,product);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Product has been updated"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable("cartItemId") int itemId,@RequestParam("token") String token) throws AuthenticationFailException, CartItemNotExistException {
        authenticationService.authenticate(token);
        long userId = authenticationService.getUser(token).getId();
        cartService.deleteCartItem(itemId, userId);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Item has been removed"), HttpStatus.OK);
    }
}
