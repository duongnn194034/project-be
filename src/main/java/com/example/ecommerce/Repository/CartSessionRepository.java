package com.example.ecommerce.Repository;

import com.example.ecommerce.Model.CartSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartSessionRepository extends JpaRepository<CartSession, Long> {
}
