package com.example.ecommerce.Repository;

import com.example.ecommerce.Model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<Comments, Long> {
}
