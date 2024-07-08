package com.example.domain;

import lombok.Getter;

import java.util.Comparator;
import java.util.List;

@Getter
public class Coupon {
    private long id;
    private double discount;

    public Coupon(long id, double discount) {
        this.id = id;
        this.discount = discount;
    }

    public static Coupon findMaxDiscount(List<Coupon> coupons) {
        return coupons.stream().max(Comparator.comparing(Coupon::getDiscount)).orElse(new Coupon(0L, 1));
    }

}
