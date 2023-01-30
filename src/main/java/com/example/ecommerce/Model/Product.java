package com.example.ecommerce.Model;

import com.example.ecommerce.Dto.Product.ProductDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, name = "product_name")
    private String productName;

    @Column(name = "product_description")
    private String productDescription;

    @Column
    private int price;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(length = 5)
    private String size;

    @Column
    private String color;

    @Column
    private String material;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @Column(name = "product_type")
    private String productType;

    @Column
    private double rate;

    @Column
    private int quantity;

    @Column(name = "created_at")
    private Date createdAt;

    public Product(ProductDto productDto, Category category) {
        this.productName = productDto.getName();
        this.imageUrl = productDto.getImageURL();
        this.productDescription = productDto.getDescription();
        this.price = productDto.getPrice();
        this.category = category;
        this.createdAt = new Date(System.currentTimeMillis());
    }

    public Product(Product product) {
        this.id = product.getId();
        this.productName = product.getProductName();
        this.productDescription = product.getProductDescription();
        this.category = product.getCategory();
        this.price = product.getPrice();
        this.imageUrl = product.getImageUrl();
    }

    public Product() {
        this.createdAt = new Date(System.currentTimeMillis());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}