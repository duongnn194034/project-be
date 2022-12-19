package com.example.ecommerce.Model;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;
    @Column(name = "total_amount")
    private int totalAmount;

    @Column(name = "total_cost")
    private int totalCost;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "order", fetch = FetchType.LAZY)
    private List<OrderItem> orderItemList;

    @Column(name = "session_id")
    private String sessionId;

    @Column(name = "created_at")
    private Date createdAt;

    @Column
    private int totalPrice;

    public String getSessionId() {
        return sessionId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void addItem(OrderItem item) {
        this.orderItemList.add(item);
    }

    public void addItems(List<OrderItem> items) {
        this.orderItemList.addAll(items);
    }

    public void removeItem(OrderItem item) {
        this.orderItemList.remove(item);
    }

    public void removeItems(List<OrderItem> items) {
        this.orderItemList.removeAll(items);
    }

    public void setCreatedDate(Date date) {
        this.createdAt = date;
    }

    public void setSessionId(String sessionId) {
    }
}
