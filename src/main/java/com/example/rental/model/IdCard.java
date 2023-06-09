package com.example.rental.model;

import java.io.Serializable;
import java.util.Date;

public class IdCard implements Serializable {
    private String name;
    private String number;
    private Date issuedOn;
    private String issuedWhere;

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
}
