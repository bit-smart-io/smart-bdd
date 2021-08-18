package component.examples.cucumber.supersimple;

public class SuperSimpleCucumberService {
    private int numberOfCucumbers;

    public int getNumberOfCucumbers() {
        return numberOfCucumbers;
    }

    public void setNumberOfCucumbers(int number) {
        this.numberOfCucumbers = number;
    }

    public void eat(int number) {
        numberOfCucumbers -= number;
    }
}
