package com.example.ecommerce.Dto.User;

import com.example.ecommerce.Enums.Role;

public class UserUpdateDto {
    private String fullname;
    private String address;
    private String phoneNumber;
    private String email;
    private Role role;

    public UserUpdateDto(String fullname, String address, String phoneNumber, String email, Role role) {
        this.fullname = fullname;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
}
