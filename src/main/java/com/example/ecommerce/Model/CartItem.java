package com.example.ecommerce.Model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "cart_items")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @Column
    private int quantity;

    @Column
    private Date createdAt;

    public CartItem() {
        this.createdAt = new Date(System.currentTimeMillis());
    }

    public CartItem(Product product, Integer quantity, User user) {
        this.product = product;
        this.quantity = quantity;
        this.user = user;
        this.createdAt = new Date(System.currentTimeMillis());
    }

//    public CartItem(CartSession cartSession) {
//        this.cartSession = cartSession;
//        this.createdAt = new Date(System.currentTimeMillis());
//    }
//
//    public CartSession getCartSession() {
//        return cartSession;
//    }
//
//    public void setCartSession(CartSession cartSession) {
//        this.cartSession = cartSession;
//    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCreatedAt(Date date) {
        this.createdAt = date;
    }
}
