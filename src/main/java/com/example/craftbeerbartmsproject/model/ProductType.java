package com.example.craftbeerbartmsproject.model;

import lombok.Getter;

@Getter
public enum ProductType {
    BEER("Beer"),
    WINE("Wine"),
    VODKA("Vodka"),
    COGNAC("Cognac"),
    WHISKEY("Whiskey"),
    CHIPS("Chips"),
    CHEESE("Cheese"),
    TOAST("Toast"),
    ONION_RINGS("Onion Rings"),
    MEAT("Meat");

    private final String name;

    ProductType(String name) {
        this.name = name;
    }
}
