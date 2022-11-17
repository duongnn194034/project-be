package com.example.ecommerce.Repository;

import com.example.ecommerce.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
