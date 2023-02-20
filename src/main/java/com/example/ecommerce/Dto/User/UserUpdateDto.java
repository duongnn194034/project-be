package com.example.ecommerce.Dto.User;

import com.example.ecommerce.Enums.Role;

public class UserUpdateDto {
    // skipping updating passord as of now
    private String fullname;
    private String address;
    private String phonenumber;
    private String email;
    private Role role;

    public UserUpdateDto(String fullname, String address, String phonenumber, String email, Role role) {
        this.fullname = fullname;
        this.address = address;
        this.phonenumber = phonenumber;
        this.email = email;
        this.role = role;
    }

    public UserUpdateDto(String fullname, String email, Role role) {
        this.fullname = fullname;
        this.email = email;
        this.role = role;
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

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
