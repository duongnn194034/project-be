package com.example.rental.enums;

public enum Status {
    IN_BOOKING("booking"),
    CANCELED("canceled"),
    OUTDATED("outdated"),
    RETURNED("returned")
    ;

    Status(String name) {

    }
}
