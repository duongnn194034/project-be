package com.example.rental.enums;

public enum Status {
    BOOKING("booking"),
    CANCELED("canceled"),
    OUTDATED("outdated"),
    RETURNED("returned"),
    COMPLETED("completed"),
    PAID("paid")
    ;

    Status(String name) {

    }
}
