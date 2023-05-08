package com.example.rental.Model;

import java.io.Serializable;
import java.time.Duration;
import java.util.Date;

public class Feature implements Serializable {
    private long id;
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

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
