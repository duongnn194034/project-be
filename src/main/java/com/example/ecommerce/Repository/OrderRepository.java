package com.example.ecommerce.Repository;

import com.example.ecommerce.Model.Order;
import com.example.ecommerce.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("select o from Order o order by o.createdAt desc")
    List<Order> findAllByUserOrderByCreatedDateDesc(User user);
}
