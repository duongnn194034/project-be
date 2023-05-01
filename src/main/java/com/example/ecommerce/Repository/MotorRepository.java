package com.example.ecommerce.Repository;

import com.example.ecommerce.Model.Motor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotorRepository extends JpaRepository<Motor, Long> {
}
