package ft.bdd.examples.cucumber.builder.builders;

import ft.bdd.examples.cucumber.builder.domain.CucumberThen;
import ft.bdd.examples.cucumber.builder.framework.ThenBuilder;

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
