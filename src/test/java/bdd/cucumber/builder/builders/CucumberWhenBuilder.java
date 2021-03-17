package bdd.cucumber.builder.builders;

import bdd.cucumber.builder.domain.CucumberWhen;
import bdd.cucumber.builder.framework.WhenBuilder;

public class CucumberWhenBuilder implements WhenBuilder<CucumberWhen> {
    private int amount;

    private CucumberWhenBuilder() {
    }

    public static CucumberWhenBuilder iEatCucumbers() {
        return new CucumberWhenBuilder();
    }

    public CucumberWhenBuilder withAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public CucumberWhen build() {
        return new CucumberWhen(amount);
    }
}
