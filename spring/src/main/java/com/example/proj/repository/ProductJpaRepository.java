package com.example.proj.repository;

import com.example.domain.Product;
import org.springframework.stereotype.Repository;

@Repository
public class ProductJpaRepository {
    public Product getById(long productId) {
        // 실질적 탐색 로직

        // 임의로 작성한 코드
        return new Product(1L, "item1", 20000);
    }
}
