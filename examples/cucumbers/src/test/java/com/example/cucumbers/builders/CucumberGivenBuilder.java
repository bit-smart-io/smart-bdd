package com.example.cucumbers.builders;

import com.example.cucumbers.model.CucumberGiven;
import io.bitsmart.bdd.report.utils.GivenBuilder;

public class CucumberGivenBuilder implements GivenBuilder<CucumberGiven> {
    private final CucumbersBuilder cucumbers;

    private CucumberGivenBuilder(CucumbersBuilder cucumbers) {
        this.cucumbers = cucumbers;
    }

    public static CucumberGivenBuilder iHave(CucumberBuilder... cucumbers) {
        return new CucumberGivenBuilder(CucumbersBuilder.cucumbers().with(cucumbers));
    }

    public CucumberGiven build() {
        return new CucumberGiven(cucumbers.build());
    }
}
