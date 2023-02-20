package com.example.ecommerce.Repository;

import com.example.ecommerce.Model.Category;
import com.example.ecommerce.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> getProductsByCategory(Category category);

    Product save(Product product);
    void deleteAllByCategory(Category category);
    void deleteProductById(long id);
}
