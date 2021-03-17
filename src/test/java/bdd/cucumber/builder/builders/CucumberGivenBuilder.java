package bdd.cucumber.builder.builders;

import bdd.cucumber.builder.domain.CucumberGiven;
import bdd.cucumber.builder.framework.GivenBuilder;

public class CucumberGivenBuilder implements GivenBuilder<CucumberGiven> {
    private int amount;

    private CucumberGivenBuilder() {
    }

    public static CucumberGivenBuilder iHaveCucumbers() {
        return new CucumberGivenBuilder();
    }

    public CucumberGivenBuilder withAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public CucumberGiven build() {
        return new CucumberGiven(amount);
    }
}
