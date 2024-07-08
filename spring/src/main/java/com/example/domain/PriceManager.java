package com.example.domain;

public class PriceManager {

    public int calculate(User user, Product product, Coupon coupon) {
        // 쿠폰 적용
        int price = product.getPrice() - (int) (1 - coupon.getDiscount());

        // 마일리지 반영
        price -= user.getMileage();
        return price;
    }
}
