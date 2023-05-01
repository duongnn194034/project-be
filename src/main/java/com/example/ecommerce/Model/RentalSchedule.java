package com.example.ecommerce.Model;

import javax.persistence.*;

@Entity
@Table
public class RentalSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private long id;

}
