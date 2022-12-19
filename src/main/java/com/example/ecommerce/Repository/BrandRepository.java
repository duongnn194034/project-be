package com.example.ecommerce.Repository;

import com.example.ecommerce.Model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    Optional<Brand> getBrandById(long id);
    Optional<Brand> getBrandByBrandName(String brandName);
    @Query("update Brand b set b = ?2 where b.id = ?1")
    void updateById(long id, Brand updatedBrand);
}
