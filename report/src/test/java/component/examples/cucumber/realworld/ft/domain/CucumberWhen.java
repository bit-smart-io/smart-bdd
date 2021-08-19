package component.examples.cucumber.realworld.ft.domain;

public class CucumberWhen {
    private final int amount;
    private final String colour;

    public CucumberWhen(int amount, String colour) {
        this.amount = amount;
        this.colour = colour;
    }

    public int getAmount() {
        return amount;
    }

    public String getColour() {
        return colour;
    }
}
