package com.example.cucumbers.model;

public class CucumberWhen {
    private final int quantity;
    private final String colour;

    public CucumberWhen(int quantity, String colour) {
        this.quantity = quantity;
        this.colour = colour;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getColour() {
        return colour;
    }
}
