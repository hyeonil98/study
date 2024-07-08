package com.example.domain;

import lombok.Getter;

@Getter
public class Product {
    private long id;
    private String name;
    private int price;

    public Product(long id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
