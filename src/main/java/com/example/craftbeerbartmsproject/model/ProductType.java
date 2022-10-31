package com.example.craftbeerbartmsproject.model;

import lombok.Getter;

@Getter
public enum ProductType {
    BEER("Beer"),
    WINE("Wine"),
    VODKA("Vodka"),
    COGNAC("Cognac"),
    WHISKEY("Whiskey"),
    SNACKS("Snacks");

    private final String name;

    ProductType(String name) {
        this.name = name;
    }
}
