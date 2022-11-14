package com.example.ecommerce.Repository;

import com.example.ecommerce.Model.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemsRepository extends JpaRepository<CartItems, Long> {
}
