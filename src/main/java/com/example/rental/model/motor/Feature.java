package com.example.rental.model.motor;

import java.io.Serializable;
import java.util.List;

public class Feature implements Serializable {
    private boolean damageInsurance;
    private boolean stolenInsurance;
    private boolean orderCanceling;
    private boolean fuelCost;
    private boolean noDeposit;
    private boolean adjust;
    private List<String> others;

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

    public boolean isNoDeposit() {
        return noDeposit;
    }

    public void setNoDeposit(boolean noDeposit) {
        this.noDeposit = noDeposit;
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
}
