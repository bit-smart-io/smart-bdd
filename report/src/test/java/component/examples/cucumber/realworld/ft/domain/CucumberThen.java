package component.examples.cucumber.realworld.ft.domain;

import component.examples.cucumber.realworld.ft.builders.CucumberBuilder;

import java.util.List;
import java.util.Optional;

public class CucumberThen {
    private final Integer amount;
    private final String colour;
    private final List<CucumberBuilder> cucumbers;

    public CucumberThen(Integer amount, String colour, List<CucumberBuilder> cucumbers) {
        this.amount = amount;
        this.colour = colour;
        this.cucumbers = cucumbers;
    }

    public Optional<Integer> getAmount() {
        return Optional.ofNullable(amount);
    }

    public Optional<String> getColour() {
        return Optional.ofNullable(colour);
    }

    public Optional<List<CucumberBuilder>> getCucumbers() {
        return Optional.ofNullable(cucumbers);
    }
}
