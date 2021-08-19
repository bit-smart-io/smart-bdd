package component.examples.cucumber.verysimple.builder.builders;

import component.examples.cucumber.verysimple.builder.domain.SimpleCucumberWhen;
import io.bitsmart.bdd.report.utils.WhenBuilder;

public class SimpleCucumberWhenBuilder implements WhenBuilder<SimpleCucumberWhen> {
    private int amount;

    private SimpleCucumberWhenBuilder() {
    }

    public static SimpleCucumberWhenBuilder iEatCucumbers() {
        return new SimpleCucumberWhenBuilder();
    }

    public SimpleCucumberWhenBuilder withAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public SimpleCucumberWhen build() {
        return new SimpleCucumberWhen(amount);
    }
}
