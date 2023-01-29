package com.example.ecommerce.Repository;

import com.example.ecommerce.Model.CartItem;
import com.example.ecommerce.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    @Query("select c from CartItem c where c.user = ?1 order by c.createdAt desc")
    List<CartItem> findAllByUserOrderByCreatedDateDesc(User user);

    @Query("delete from CartItem c where c.user = ?1")
    void deleteByUser(User user);

    @Query("select count(c) from CartItem c")
    int countAll(User user);
}
