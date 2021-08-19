package component.examples.cucumber.realworld.ft.builders;

import component.examples.cucumber.realworld.ft.domain.CucumberThen;
import io.bitsmart.bdd.report.utils.ThenBuilder;

import java.util.List;

import static java.util.Arrays.asList;

public class CucumberThenBuilder implements ThenBuilder<CucumberThen> {
    private Integer amount;
    private String colour;
    private List<CucumberBuilder> cucumbers;

    private CucumberThenBuilder() {
    }

    public static CucumberThenBuilder iShouldHaveCucumbers() {
        return new CucumberThenBuilder();
    }

    public static CucumberThenBuilder iShouldHave(CucumberBuilder... cucumbers) {
        return new CucumberThenBuilder().with(cucumbers);
    }

    public CucumberThenBuilder withAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public CucumberThenBuilder withColour(String colour) {
        this.colour = colour;
        return this;
    }

    public CucumberThenBuilder with(CucumberBuilder... cucumbers) {
        this.cucumbers = asList(cucumbers);
        return this;
    }


    public CucumberThen build() {
        return new CucumberThen(amount, colour, cucumbers);
    }


}
