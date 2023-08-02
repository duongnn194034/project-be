package com.example.rental.model.user;

import com.example.rental.enums.Role;
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
    private String avatarUrl;
    private String zaloUrl;
    private String facebookUrl;
    private String twitterUrl;
    private String biography;
    private boolean verified;
    private Address address;
    private License license;
    private IdCard idCard;
    private Bank bank;
    private Date createdAt;

    public User() {
        this.createdAt = new Date(System.currentTimeMillis());
    }

    public User(String id, String fullName) {
        this.id = id;
        this.fullName = fullName;
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

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public License getLicense() {
        return license;
    }

    public void setLicense(License license) {
        this.license = license;
    }

    public IdCard getIdCard() {
        return idCard;
    }

    public void setIdCard(IdCard idCard) {
        this.idCard = idCard;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public String getZaloUrl() {
        return zaloUrl;
    }

    public void setZaloUrl(String zaloUrl) {
        this.zaloUrl = zaloUrl;
    }

    public String getFacebookUrl() {
        return facebookUrl;
    }

    public void setFacebookUrl(String facebookUrl) {
        this.facebookUrl = facebookUrl;
    }

    public String getTwitterUrl() {
        return twitterUrl;
    }

    public void setTwitterUrl(String twitterUrl) {
        this.twitterUrl = twitterUrl;
    }
}
