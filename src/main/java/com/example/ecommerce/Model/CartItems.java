package com.example.ecommerce.Model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "cart_items")
public class CartItems {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "cart_session_id")
    private Long cartSessionId;

    @Column(name = "product_id")
    private Long productId;

    @Column
    private int quantity;

    @Column
    private Date createdAt;

    public CartItems() {
    }

    public Long getCartSessionId() {
        return cartSessionId;
    }

    public void setCartSessionId(Long cartSessionId) {
        this.cartSessionId = cartSessionId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
