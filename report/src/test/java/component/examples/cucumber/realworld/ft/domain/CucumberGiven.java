package component.examples.cucumber.realworld.ft.domain;

import component.examples.cucumber.realworld.app.model.Cucumber;

import java.util.List;

public class CucumberGiven {
    private final List<Cucumber> cucumbers;

    public CucumberGiven(List<Cucumber> cucumbers) {
        this.cucumbers = cucumbers;
    }

    public List<Cucumber> getCucumbers() {
        return cucumbers;
    }
}
