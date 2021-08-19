package component.examples.cucumber.realworld.ft.builders;

import component.examples.cucumber.realworld.ft.domain.CucumberWhen;
import io.bitsmart.bdd.report.utils.WhenBuilder;

public class CucumberWhenBuilder implements WhenBuilder<CucumberWhen> {
    private int amount;
    private String colour;

    private CucumberWhenBuilder() {
    }

    public static CucumberWhenBuilder iRequestToEatCucumbers() {
        return new CucumberWhenBuilder();
    }

    public CucumberWhenBuilder withAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public CucumberWhenBuilder withColour(String colour) {
        this.colour = colour;
        return this;
    }

    public CucumberWhen build() {
        return new CucumberWhen(amount, colour);
    }
}
