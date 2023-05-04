package com.example.rental.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Duration;
import java.util.Date;

@Entity
@Table
public class Feature implements Serializable {
    @Id
    @OneToOne(targetEntity = RentalOffer.class)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private long id;

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
    @Column(name = "min_age")
    private int minAge;
    @Column(name = "min_driving")
    private int minDriving;
    @Column(name = "start_rent")
    private Date startRent;
    @Column(name = "end_rent")
    private Date endRent;
    @Column(name = "min_duration")
    private Duration minDur;
    @Column(name = "max_duration")
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
