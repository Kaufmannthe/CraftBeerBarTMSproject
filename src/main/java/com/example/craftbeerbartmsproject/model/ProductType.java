package com.example.craftbeerbartmsproject.model;

public enum ProductType {
    BEER {
        @Override
        public String toString() {
            return "Beer";
        }
    },
    WINE {
        @Override
        public String toString() {
            return "Wine";
        }
    },
    VODKA {
        @Override
        public String toString() {
            return "Vodka";
        }
    },
    COGNAC {
        @Override
        public String toString() {
            return "Cognac";
        }
    },
    WHISKEY {
        @Override
        public String toString() {
            return "Whiskey";
        }
    },
    CHIPS {
        @Override
        public String toString() {
            return "Chips";
        }
    },
    CHEESE {
        @Override
        public String toString() {
            return "Cheese";
        }
    },
    TOAST {
        @Override
        public String toString() {
            return "Toast";
        }
    },
    ONION_RINGS {
        @Override
        public String toString() {
            return "Onion Rings";
        }
    },
    MEAT {
        @Override
        public String toString() {
            return "Meat";
        }
    }
}
