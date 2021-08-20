package com.example.cucumbers.builders;

import com.example.cucumbers.model.Cucumber;
import io.bitsmart.bdd.report.utils.Builder;

public final class CucumberBuilder implements Builder<Cucumber> {
    private int size;
    private String colour;

    private CucumberBuilder() {
    }

    public static CucumberBuilder aCucumber() {
        return new CucumberBuilder();
    }

    public static CucumberBuilder andACucumber() {
        return aCucumber();
    }

    public CucumberBuilder withSize(int size) {
        this.size = size;
        return this;
    }

    public CucumberBuilder withColour(String colour) {
        this.colour = colour;
        return this;
    }

    public Cucumber build() {
        return new Cucumber(size, colour);
    }
}
