package com.example.ecommerce.Model;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart_sessions")
public class CartSession {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "cartSession", fetch = FetchType.LAZY)
    private List<CartItem> cartItemList;

    @Column(name = "total_amount")
    private int totalAmount;

    @Column(name = "created_at")
    private Date createdAt;

    public CartSession() {
        this.createdAt = new Date(System.currentTimeMillis());
    }

    public CartSession(List<CartItem> items, Integer quantity, User user) {
        this.totalAmount = quantity;
        this.user = user;
        this.cartItemList = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
