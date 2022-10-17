package com.example.craftbeerbartmsproject.model;

import lombok.Getter;

@Getter
public enum OrderStatus {

    NEW("Processing"),
    PRODUCER_CONFIRM("Order is ready"),
    COURIER_DELIVERING("The order is delivered by "),
    DELIVERED("Delivered"),
    PROBLEMS("Proceedings on order");

    private final String name;

    OrderStatus(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
