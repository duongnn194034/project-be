package com.example.rental.Model;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class RentalSchedule {
    @Id
    private long id;
    private RentalOffer rentalOffer;
    private Date startTime;

    private Date endTime;

    public RentalOffer getRentalOffer() {
        return rentalOffer;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
