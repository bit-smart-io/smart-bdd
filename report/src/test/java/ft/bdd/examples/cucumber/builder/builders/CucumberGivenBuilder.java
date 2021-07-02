package ft.bdd.examples.cucumber.builder.builders;

import ft.bdd.examples.cucumber.builder.domain.CucumberGiven;
import io.bitsmart.bdd.report.utils.GivenBuilder;

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
