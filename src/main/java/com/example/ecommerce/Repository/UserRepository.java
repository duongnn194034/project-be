package com.example.ecommerce.Repository;

import com.example.ecommerce.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT u from User u where u.email = ?1")
    User findByEmail(String email);
}
