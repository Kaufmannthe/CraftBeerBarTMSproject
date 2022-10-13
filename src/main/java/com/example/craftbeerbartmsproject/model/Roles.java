package com.example.craftbeerbartmsproject.model;

import lombok.Getter;

@Getter
public enum Roles {
    USER("User"),
    MODERATOR("Moderator"),
    ADMIN("Administrator"),
    COURIER("Courier");

    private final String name;

    Roles(String name) {
        this.name = name;
    }
}
