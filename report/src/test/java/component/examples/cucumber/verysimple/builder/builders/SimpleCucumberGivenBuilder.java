package component.examples.cucumber.verysimple.builder.builders;

import component.examples.cucumber.verysimple.builder.domain.SimpleCucumberGiven;
import io.bitsmart.bdd.report.utils.GivenBuilder;

public class SimpleCucumberGivenBuilder implements GivenBuilder<SimpleCucumberGiven> {
    private int amount;

    private SimpleCucumberGivenBuilder() {
    }

    public static SimpleCucumberGivenBuilder iHaveCucumbers() {
        return new SimpleCucumberGivenBuilder();
    }

    public SimpleCucumberGivenBuilder withAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public SimpleCucumberGiven build() {
        return new SimpleCucumberGiven(amount);
    }
}
