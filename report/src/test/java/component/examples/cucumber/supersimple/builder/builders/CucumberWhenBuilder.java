package component.examples.cucumber.supersimple.builder.builders;

import component.examples.cucumber.supersimple.builder.domain.CucumberWhen;
import io.bitsmart.bdd.report.utils.WhenBuilder;

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
