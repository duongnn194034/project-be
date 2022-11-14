package com.example.ecommerce.Repository;

import com.example.ecommerce.Model.Brands;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandsRepository extends JpaRepository<Brands, Long> {
}
