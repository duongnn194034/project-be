package com.example.rental.model;

import java.io.Serializable;
import java.time.Duration;
import java.util.Date;
import java.util.List;

public class Feature implements Serializable {
    private boolean damageInsurance;
    private boolean stolenInsurance;
    private boolean orderCanceling;
    private boolean fuelCost;
    private boolean adjust;
    private List<String> others;
    private int minAge;
    private int minDriving;
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

    public String getMinDur() {
        return durToString(minDur);
    }

    public void setMinDur(Duration minDur) {
        this.minDur = minDur;
    }

    public String getMaxDur() {
        return durToString(maxDur);
    }

    public void setMaxDur(Duration maxDur) {
        this.maxDur = maxDur;
    }

    public List<String> getOthers() {
        return others;
    }

    public void setOthers(List<String> others) {
        this.others = others;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    private String durToString(Duration duration) {
        if (duration == null) {
            return "0 hour";
        }
        StringBuilder stringBuilder = new StringBuilder();
        long days = duration.toDays();
        if (days > 1) {
            stringBuilder.append(days).append(" days ");
        } else if (days > 0) {
            stringBuilder.append(days).append(" day ");
        }
        long hours = duration.minusDays(days).toHours();
        if (hours > 1) {
            stringBuilder.append(hours).append(" hours");
        } else {
            stringBuilder.append(hours).append(" hour");
        }
        return stringBuilder.toString();
    }
}
