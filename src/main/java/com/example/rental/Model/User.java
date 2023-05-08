package com.example.rental.Model;

import com.example.rental.Enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("User")
public class User {
    @Id
    private String id;
    private String email;
    @JsonIgnore
    private String password;
    private String fullName;
    private String phoneNumber;
    private Role role;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
