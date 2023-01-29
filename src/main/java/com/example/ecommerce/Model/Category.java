package com.example.ecommerce.Model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "category_name", length = 50)
    private String categoryName;

    @Column(name = "image_url")
    private String imageUrl;

    @Column
    private String description;

    @Column(name = "created_at")
    private Date createdAt;

    public Category() {
        this.createdAt = new Date(System.currentTimeMillis());
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
