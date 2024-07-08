package com.example.proj.service;

import com.example.domain.Coupon;
import com.example.domain.PriceManager;
import com.example.domain.Product;
import com.example.domain.User;
import com.example.proj.repository.CouponJpaRepository;
import com.example.proj.repository.PostJpaRepository;
import com.example.proj.repository.ProductJpaRepository;
import com.example.proj.repository.UserJpaRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

import static com.example.domain.Coupon.findMaxDiscount;

@Service
public class ProductService {
    private final UserJpaRepository userJpaRepository;
    private final ProductJpaRepository productJpaRepository;
    private final CouponJpaRepository couponJpaRepository;

    public ProductService(UserJpaRepository userJpaRepository, ProductJpaRepository productJpaRepository, CouponJpaRepository couponJpaRepository) {
        this.userJpaRepository = userJpaRepository;
        this.productJpaRepository = productJpaRepository;
        this.couponJpaRepository = couponJpaRepository;
    }

    // 최대 할인율을 찾는 코드
    public int calculatePrice(long userId, long productId) {
        User user = userJpaRepository.getById(userId);
        Product product = productJpaRepository.getById(productId);
        List<Coupon> coupons = couponJpaRepository.getByUserId(userId);

        Coupon maxDiscountCoupon = findMaxDiscount(coupons);
        PriceManager priceManager = new PriceManager();

        return priceManager.calculate(user, product, maxDiscountCoupon);
    }


}
