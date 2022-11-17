package com.example.ecommerce.Repository;

import com.example.ecommerce.Model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
}
