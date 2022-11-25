package com.example.ecommerce.Repository;

import com.example.ecommerce.Model.CartSession;
import com.example.ecommerce.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartSessionRepository extends JpaRepository<CartSession, Long> {
    void deleteByUser(User user);

    @Query("select u from User u order by u.createdAt desc")
    List<CartSession> findAllByUserOrderByCreatedDateDesc(User user);

}
