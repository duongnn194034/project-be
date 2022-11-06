package com.example.ecommeren.Model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "cart_sessions")
public class CartSession {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "total_amount")
    private int totalAmount;

    @Column(name = "created_at")
    private Date createdAt;
}
