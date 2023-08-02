package com.example.rental.model.user;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

@Document("Token")
public class AuthenticationToken {

    @Id
    private String id;
    private String token;
    private Date createdDate;
    private User user;

    public AuthenticationToken(User user) {
        this.user = user;
        this.createdDate = new Date();
        this.token = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AuthenticationToken(String id, String token, Date createdDate, User user) {
        this.id = id;
        this.token = token;
        this.createdDate = createdDate;
        this.user = user;
    }

    public AuthenticationToken() {
    }
}
