package com.example.rental.model;

import java.io.Serializable;
import java.time.Duration;
import java.util.Date;

public class Feature implements Serializable {
    private boolean damageInsurance;
    private boolean stolenInsurance;
    private boolean orderCanceling;
    private boolean fuelCost;
    private boolean adjust;
    private int minAge;
    private int minDriving;
    private Date startRent;
    private Date endRent;
    private Duration minDur;
    private Duration maxDur;

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

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public int getMinDriving() {
        return minDriving;
    }

    public void setMinDriving(int minDriving) {
        this.minDriving = minDriving;
    }

    public Date getStartRent() {
        return startRent;
    }

    public void setStartRent(long startRent) {
        this.startRent = new Date(startRent);
    }

    public Date getEndRent() {
        return endRent;
    }

    public void setEndRent(long endRent) {
        this.endRent = new Date(endRent);
    }

    public Duration getMinDur() {
        return minDur;
    }

    public void setMinDur(Duration minDur) {
        this.minDur = minDur;
    }

    public Duration getMaxDur() {
        return maxDur;
    }

    public void setMaxDur(Duration maxDur) {
        this.maxDur = maxDur;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
