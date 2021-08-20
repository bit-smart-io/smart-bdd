package com.example.cucumbers.model;

import java.util.Objects;

public class Cucumber {
    private final int size;
    private final String colour;

    public Cucumber(int size, String colour) {
        this.size = size;
        this.colour = colour;
    }

    public int getSize() {
        return size;
    }

    public String getColour() {
        return colour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cucumber)) return false;
        Cucumber cucumber = (Cucumber) o;
        return size == cucumber.size && Objects.equals(colour, cucumber.colour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, colour);
    }
}
