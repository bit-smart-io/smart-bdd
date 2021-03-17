package bdd.cucumber.builder.builders;

import bdd.cucumber.builder.domain.CucumberThen;
import bdd.cucumber.builder.framework.ThenBuilder;

public class CucumberThenBuilder implements ThenBuilder<CucumberThen> {
    private int amount;

    private CucumberThenBuilder() {
    }

    public static CucumberThenBuilder iShouldHaveCucumbers() {
        return new CucumberThenBuilder();
    }

    public CucumberThenBuilder withAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public CucumberThen build() {
        return new CucumberThen(amount);
    }
}
