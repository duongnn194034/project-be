package com.example.ecommerce.Service;

import com.example.ecommerce.Dto.Cart.CartDto;
import com.example.ecommerce.Dto.Cart.CartItemDto;
import com.example.ecommerce.Dto.Checkout.CheckoutItemDto;
import com.example.ecommerce.Exception.OrderNotFoundException;
import com.example.ecommerce.Model.Order;
import com.example.ecommerce.Model.OrderItem;
import com.example.ecommerce.Model.User;
import com.example.ecommerce.Repository.OrderItemsRepository;
import com.example.ecommerce.Repository.OrderRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;


@Service
@Transactional
public class OrderService {

    @Autowired
    private CartService cartService;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemsRepository orderItemsRepository;

    @Value("${BASE_URL}")
    private String baseURL;

    @Value("${STRIPE_SECRET_KEY}")
    private String apiKey;

    // create total price
    SessionCreateParams.LineItem.PriceData createPriceData(CheckoutItemDto checkoutItemDto) {
        return SessionCreateParams.LineItem.PriceData.builder()
                .setCurrency("VND")
                .setUnitAmount((long)(checkoutItemDto.getPrice()))
                .setProductData(
                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                .setName(checkoutItemDto.getName())
                                .build())
                .build();
    }

    // build each product in the stripe checkout page
    SessionCreateParams.LineItem createSessionLineItem(CheckoutItemDto checkoutItemDto) {
        return SessionCreateParams.LineItem.builder()
                // set price for each product
                .setPriceData(createPriceData(checkoutItemDto))
                // set quantity for each product
                .setQuantity(Long.parseLong(String.valueOf(checkoutItemDto.getQuantity())))
                .build();
    }

    // create session from list of checkout items
    public Session createSession(List<CheckoutItemDto> checkoutItemDtoList) throws StripeException {

        // supply success and failure url for stripe
        String successURL = baseURL + "payment/success";
        String failedURL = baseURL + "payment/failed";

        // set the private key
        Stripe.apiKey = apiKey;

        List<SessionCreateParams.LineItem> sessionItemsList = new ArrayList<>();

        // for each product compute SessionCreateParams.LineItem
        for (CheckoutItemDto checkoutItemDto : checkoutItemDtoList) {
            sessionItemsList.add(createSessionLineItem(checkoutItemDto));
        }

        // build the session param
        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setCancelUrl(failedURL)
                .addAllLineItem(sessionItemsList)
                .setSuccessUrl(successURL)
                .setCurrency("VND")
                .build();
        return Session.create(params);
    }

    public void placeOrder(User user, String sessionId, String status) {
        // first let get cart items for the user
        CartDto cartDto = cartService.listCartItems(user);

        List<CartItemDto> cartItemDtoList = cartDto.getcartItems();

        // create the order and save it
        Order newOrder = new Order();
        newOrder.setCreatedDate(new java.sql.Date(System.currentTimeMillis()));
        newOrder.setSessionId(sessionId);
        newOrder.setUser(user);
        newOrder.setTotalCost(cartDto.getTotalCost());
        newOrder.setStatus(status);
        orderRepository.save(newOrder);

        for (CartItemDto cartItemDto : cartItemDtoList) {
            // create orderItem and save each one
            OrderItem orderItem = new OrderItem();
            orderItem.setCreatedDate(new java.sql.Date(System.currentTimeMillis()));
            orderItem.setPrice(cartItemDto.getProduct().getPrice());
            orderItem.setProduct(cartItemDto.getProduct());
            orderItem.setQuantity(cartItemDto.getQuantity());
            orderItem.setOrder(newOrder);
            // add to order item list
            orderItemsRepository.save(orderItem);
        }
        //
        cartService.deleteUserCartItems(user);
    }

    public List<Order> listOrders(User user) {
        return orderRepository.findAllByUserOrderByCreatedDateDesc(user);
    }


    public Order getOrder(long orderId) throws OrderNotFoundException {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent()) {
            return order.get();
        }
        throw new OrderNotFoundException("Order not found");
    }

    public void updateOrderStatus(long orderId, String status) {
        Order order = getOrder(orderId);
        order.setStatus(status);
        orderRepository.save(order);
    }

    public Order getOrderBySessionId(String sessionId) {
        return orderRepository.findOneBySessionId(sessionId);
    }

    public void deleteOrderById(long orderId) {
        orderRepository.deleteOrdersById(orderId);
    }
}


