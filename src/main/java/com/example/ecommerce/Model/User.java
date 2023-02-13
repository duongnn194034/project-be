package com.example.ecommerce.Model;

import com.example.ecommerce.Enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @JsonIgnore
    @Column(nullable = false, length = 64)
    private String password;

    @Column(nullable = false)
    private String fullName;

    @Column(unique = true, length = 15)
    private String phoneNumber;

    @Column
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @JsonIgnore
    @OneToMany(mappedBy = "user",
            fetch = FetchType.LAZY)
    private List<Order> orders;

    @Column(name = "created_at")
    private Date createdAt;

    public User() {
        this.createdAt = new Date(System.currentTimeMillis());
    }

    public User(String fullName, String email, Role role, String encryptedPassword) {
        this.fullName = fullName;
        this.email = email;
        this.role = role;
        this.password = encryptedPassword;
        this.createdAt = new Date(System.currentTimeMillis());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullName;
    }

    public void setFullname(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
