package com.example.rental.dto.user;

import com.example.rental.enums.Role;

public class UserUpdateDto {
    private String fullname;
    private String phoneNumber;
    private String email;
    private String avatarUrl;
    private String biography;
    private String fbUrl;
    private String zaloUrl;
    private String twitterUrl;
    private Role role;

    public UserUpdateDto(String fullname, String phoneNumber, String email, Role role) {
        this.fullname = fullname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.role = role;
    }

    public UserUpdateDto(String fullname, String email, Role role) {
        this.fullname = fullname;
        this.email = email;
        this.role = role;
    }

    public UserUpdateDto() {

    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getFbUrl() {
        return fbUrl;
    }

    public void setFbUrl(String fbUrl) {
        this.fbUrl = fbUrl;
    }

    public String getZaloUrl() {
        return zaloUrl;
    }

    public void setZaloUrl(String zaloUrl) {
        this.zaloUrl = zaloUrl;
    }

    public String getTwitterUrl() {
        return twitterUrl;
    }

    public void setTwitterUrl(String twitterUrl) {
        this.twitterUrl = twitterUrl;
    }
}
