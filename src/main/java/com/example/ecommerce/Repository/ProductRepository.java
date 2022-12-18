package com.example.ecommerce.Repository;

import com.example.ecommerce.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("update Product p set p = ?2 where p.id = ?1")
    void updateById(Long id, Product product);
}
