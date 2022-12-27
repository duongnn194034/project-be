package com.example.ecommerce.Repository;

import com.example.ecommerce.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> getCategoryById(long id);
    Optional<Category> getCategoryByCategoryName(String categoryName);
    @Query("update Category b set b = ?2 where b.id = ?1")
    void updateById(long id, Category updatedCategory);
}
