package com.example.cucumbers.builders;

import com.example.cucumbers.model.CucumberWhen;
import io.bitsmart.bdd.report.utils.WhenBuilder;

public class CucumberWhenBuilder implements WhenBuilder<CucumberWhen> {
    private int quantity;
    private String colour;

    private CucumberWhenBuilder() {
    }

    public static CucumberWhenBuilder iRequestToEatCucumbers() {
        return new CucumberWhenBuilder();
    }

    public CucumberWhenBuilder withQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public CucumberWhenBuilder withColour(String colour) {
        this.colour = colour;
        return this;
    }

    public CucumberWhen build() {
        return new CucumberWhen(quantity, colour);
    }
}
