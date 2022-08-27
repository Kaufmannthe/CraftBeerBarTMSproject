package com.example.craftbeerbartmsproject.model;

public enum Roles {
    USER {
        @Override
        public String toString() {
            return "User";
        }
    },
    MODERATOR {
        @Override
        public String toString() {
            return "Moderator";
        }
    },
    ADMIN {
        @Override
        public String toString() {
            return "Administrator";
        }
    }
}
