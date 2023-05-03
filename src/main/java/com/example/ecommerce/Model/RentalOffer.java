package com.example.ecommerce.Model;

import javax.persistence.*;

@Entity
@Table
public class RentalOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private long id;
    @OneToOne(targetEntity = Motor.class)
    @JoinColumn(name = "motor_id", referencedColumnName = "id")
    private Motor motor;
    private double latPos;
    private double lngPos;
    private double price;

    @Column(name = "damage_insurance")
    private boolean damageInsurance;
    @Column(name = "stolen_insurance")
    private boolean stolenInsurance;
    @Column(name = "order_canceling")
    private boolean orderCanceling;
    @Column(name = "fuel_cost")
    private boolean fuelCost;
    @Column(name = "adjust")
    private boolean adjust;

    public boolean isDamageInsurance() {
        return damageInsurance;
    }

    public void setDamageInsurance(boolean damageInsurance) {
        this.damageInsurance = damageInsurance;
    }

    public boolean isStolenInsurance() {
        return stolenInsurance;
    }

    public void setStolenInsurance(boolean stolenInsurance) {
        this.stolenInsurance = stolenInsurance;
    }

    public boolean isOrderCanceling() {
        return orderCanceling;
    }

    public void setOrderCanceling(boolean orderCanceling) {
        this.orderCanceling = orderCanceling;
    }

    public boolean isFuelCost() {
        return fuelCost;
    }

    public void setFuelCost(boolean fuelCost) {
        this.fuelCost = fuelCost;
    }

    public boolean isAdjust() {
        return adjust;
    }

    public void setAdjust(boolean adjust) {
        this.adjust = adjust;
    }

    public long getId() {
        return id;
    }

    public double getLatPos() {
        return latPos;
    }

    public void setLatPos(double latPos) {
        this.latPos = latPos;
    }

    public double getLngPos() {
        return lngPos;
    }

    public void setLngPos(double lngPos) {
        this.lngPos = lngPos;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
