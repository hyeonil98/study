package com.example.proj.repository;

import com.example.domain.Coupon;
import com.example.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class CouponJpaRepository {
    public List<Coupon> getByUserId(long userId){
        // 실질적 탐색 로직

        // 임의로 작성한 코드
        return Arrays.asList(new Coupon(1L, 0.3), new Coupon(2L, 0.2));
    }
}
