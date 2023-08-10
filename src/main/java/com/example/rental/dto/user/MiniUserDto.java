package com.example.rental.dto.user;

public class MiniUserDto {
    private String userName;
    private String email;
    private String phoneNumber;
    private String avatarUrl;

    public MiniUserDto(String userName) {
        this.userName = userName;
    }

    public MiniUserDto(String userName, String email, String phoneNumber, String avatarUrl) {
        this.userName = userName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.avatarUrl = avatarUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
