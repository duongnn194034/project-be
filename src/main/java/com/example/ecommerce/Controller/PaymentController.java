package com.example.ecommerce.Controller;

import com.example.ecommerce.Model.Order;
import com.example.ecommerce.Service.OrderService;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private OrderService orderService;
    @GetMapping("/success")
    public String paymentSuccessHandler(@RequestParam("session_id") String sessionId) throws StripeException {
        Session session = Session.retrieve(sessionId);
        Order order = orderService.getOrderBySessionId(sessionId);
        if (order != null) {
            orderService.updateOrderStatus(order.getId(), session.getPaymentStatus());
        }
        return "redirect:http://localhost:8081";
    }
}
