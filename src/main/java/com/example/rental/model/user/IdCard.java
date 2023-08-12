package com.example.rental.model.user;

import java.io.Serializable;
import java.util.Date;

public class IdCard implements Serializable {
    private String name;
    private String number;
    private Date issuedOn;
    private String issuedWhere;
    private String front;
    private String back;
    private String selfie;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getIssuedOn() {
        return issuedOn;
    }

    public void setIssuedOn(Date issuedOn) {
        this.issuedOn = issuedOn;
    }

    public String getIssuedWhere() {
        return issuedWhere;
    }

    public void setIssuedWhere(String issuedWhere) {
        this.issuedWhere = issuedWhere;
    }

    public String getFront() {
        return front;
    }

    public void setFront(String front) {
        this.front = front;
    }

    public String getBack() {
        return back;
    }

    public void setBack(String back) {
        this.back = back;
    }

    public String getSelfie() {
        return selfie;
    }

    public void setSelfie(String selfie) {
        this.selfie = selfie;
    }
}
