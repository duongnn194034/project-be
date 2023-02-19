package com.example.ecommerce.Controller;

import com.example.ecommerce.Common.ApiResponse;
import com.example.ecommerce.Dto.Checkout.CheckoutItemDto;
import com.example.ecommerce.Dto.Checkout.StripeResponse;
import com.example.ecommerce.Exception.AuthenticationFailException;
import com.example.ecommerce.Exception.OrderNotFoundException;
import com.example.ecommerce.Model.Order;
import com.example.ecommerce.Model.User;
import com.example.ecommerce.Service.AuthenticationService;
import com.example.ecommerce.Service.OrderService;
import com.example.ecommerce.Service.UserService;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private UserService userService;


    // stripe create session API
    @PostMapping("/create-checkout-session")
    public ResponseEntity<StripeResponse> checkoutList(@RequestBody List<CheckoutItemDto> checkoutItemDtoList, @RequestParam("token") String token) throws StripeException {
        // create the stripe session
        Session session = orderService.createSession(checkoutItemDtoList);
        StripeResponse stripeResponse = new StripeResponse(session.getId());
        return new ResponseEntity<>(stripeResponse, HttpStatus.OK);
    }

    // place order after checkout
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> placeOrder(@RequestParam("token") String token, @RequestParam("sessionId") String sessionId)
            throws AuthenticationFailException {
        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);
        String status = "unpaid";
        try {
            Session session = Session.retrieve(sessionId);
            status = session.getPaymentStatus();
        } catch (Exception e) {
            System.out.println("Error while retrieving sessionId");
        }
        // place the order
        orderService.placeOrder(user, sessionId, status);
        return new ResponseEntity<>(new ApiResponse(true, "Order has been placed"), HttpStatus.CREATED);
    }

    // get all orders
    @GetMapping("/")
    public ResponseEntity<List<Order>> getAllOrders(@RequestParam("token") String token) throws AuthenticationFailException {
        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);
        // get orders
        List<Order> orderDtoList = orderService.listOrders(user);

        return new ResponseEntity<>(orderDtoList, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Object> getOrderById(@PathVariable("id") long id, @RequestParam("token") String token)
            throws AuthenticationFailException {
        // validate token
        authenticationService.authenticate(token);
        try {
            Order order = orderService.getOrder(id);
            return new ResponseEntity<>(order, HttpStatus.OK);
        }
        catch (OrderNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteOrderById(@PathVariable("id") long id, @RequestParam("token") String token)
            throws AuthenticationFailException {
        // validate token
        authenticationService.authenticate(token);
        try {
            orderService.deleteOrderById(id);
            return new ResponseEntity<>("OK", HttpStatus.OK);
        }
        catch (OrderNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
