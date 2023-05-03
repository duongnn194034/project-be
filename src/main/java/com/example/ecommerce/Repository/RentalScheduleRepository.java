package com.example.ecommerce.Repository;

import com.example.ecommerce.Model.RentalSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalScheduleRepository extends JpaRepository<RentalSchedule, Long> {
}
