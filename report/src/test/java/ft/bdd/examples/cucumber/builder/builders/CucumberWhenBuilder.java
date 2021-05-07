package ft.bdd.examples.cucumber.builder.builders;

import ft.bdd.examples.cucumber.builder.domain.CucumberWhen;
import ft.bdd.examples.cucumber.builder.framework.WhenBuilder;

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
