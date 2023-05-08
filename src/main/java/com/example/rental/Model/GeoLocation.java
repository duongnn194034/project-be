package com.example.rental.Model;

import javax.persistence.*;

@Entity
@Table(name = "geo_loc")
public class GeoLocation {
    @Id
    @OneToOne(targetEntity = RentalOffer.class)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private long id;
    private double latPos;
    private double lngPos;

    private String town;
    private String city;

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

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
