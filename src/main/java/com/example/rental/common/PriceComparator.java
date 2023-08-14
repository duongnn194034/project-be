package com.example.rental.common;

import com.example.rental.dto.user.UserPriceDto;

import java.util.Comparator;

public class PriceComparator implements Comparator<UserPriceDto> {
    @Override
    public int compare(UserPriceDto o1, UserPriceDto o2) {
        return Double.compare(o2.getIncome(), o1.getIncome());
    }
}
