package com.example.ecommerce.Model;

import javax.persistence.*;

@Entity
@Table(name = "Motor")
public class Motor extends Vehicle {
    private int cylinder;

    public int getCylinder() {
        return cylinder;
    }

    public void setCylinder(int cylinder) {
        this.cylinder = cylinder;
    }
}
