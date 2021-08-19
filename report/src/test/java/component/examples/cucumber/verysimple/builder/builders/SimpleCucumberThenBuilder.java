package component.examples.cucumber.verysimple.builder.builders;

import component.examples.cucumber.verysimple.builder.domain.SimpleCucumberThen;
import io.bitsmart.bdd.report.utils.ThenBuilder;

public class SimpleCucumberThenBuilder implements ThenBuilder<SimpleCucumberThen> {
    private int amount;

    private SimpleCucumberThenBuilder() {
    }

    public static SimpleCucumberThenBuilder iShouldHaveCucumbers() {
        return new SimpleCucumberThenBuilder();
    }

    public SimpleCucumberThenBuilder withAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public SimpleCucumberThen build() {
        return new SimpleCucumberThen(amount);
    }
}
