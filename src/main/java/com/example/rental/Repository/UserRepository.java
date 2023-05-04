package com.example.rental.Repository;

import com.example.rental.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT u from User u where u.email = ?1")
    User findByEmail(String email);

    List<User> findAll();
}
