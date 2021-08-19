package component.examples.cucumber.realworld.ft.builders;

import component.examples.cucumber.realworld.ft.domain.CucumberGiven;
import io.bitsmart.bdd.report.utils.GivenBuilder;

import static component.examples.cucumber.realworld.ft.builders.CucumbersBuilder.cucumbers;

public class CucumberGivenBuilder implements GivenBuilder<CucumberGiven> {
    private final CucumbersBuilder cucumbers;

    private CucumberGivenBuilder(CucumbersBuilder cucumbers) {
        this.cucumbers = cucumbers;
    }

    public static CucumberGivenBuilder iHave(CucumberBuilder... cucumbers) {
        return new CucumberGivenBuilder(cucumbers().with(cucumbers));
    }

    public CucumberGiven build() {
        return new CucumberGiven(cucumbers.build());
    }
}
