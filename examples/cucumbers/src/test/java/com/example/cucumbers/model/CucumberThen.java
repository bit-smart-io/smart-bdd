package com.example.cucumbers.model;

import com.example.cucumbers.builders.CucumberBuilder;

import java.util.List;
import java.util.Optional;

public class CucumberThen {
    private final Integer quantity;
    private final String colour;
    private final List<CucumberBuilder> cucumbers;

    public CucumberThen(Integer quantity, String colour, List<CucumberBuilder> cucumbers) {
        this.quantity = quantity;
        this.colour = colour;
        this.cucumbers = cucumbers;
    }

    public Optional<Integer> getQuantity() {
        return Optional.ofNullable(quantity);
    }

    public Optional<String> getColour() {
        return Optional.ofNullable(colour);
    }

    public Optional<List<CucumberBuilder>> getCucumbers() {
        return Optional.ofNullable(cucumbers);
    }
}
