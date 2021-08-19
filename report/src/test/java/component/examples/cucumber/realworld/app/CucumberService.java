package component.examples.cucumber.realworld.app;

import component.examples.cucumber.realworld.app.model.Cucumber;
import component.examples.cucumber.realworld.app.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CucumberService {
    private final List<Cucumber> cucumbers = new ArrayList<>();
    private final User user = new User(true);

    public void setHungry(boolean isHungry) {
       user.setHungry(isHungry);
    }

    public void setCucumbers(List<Cucumber> cucumbers) {
        this.cucumbers.clear();
        this.cucumbers.addAll(cucumbers);
    }

    public List<Cucumber> getCucumbers() {
        return cucumbers;
    }

    public void eat(int amount, String colour) {
        if (user.isHungry()) {
            List<Cucumber> matchedCucumbers = cucumbers.stream()
                .filter(cucumber -> colour.equals(cucumber.getColour()))
                .limit(amount)
                .collect(Collectors.toList());

            cucumbers.removeAll(matchedCucumbers);
        }
    }

//    public void eatAllThatMatchColour(String colour) {
//        List<Cucumber> matchedCucumbers = cucumbers.stream()
//            .filter(cucumber -> colour.equals(cucumber.getColour()))
//            .collect(Collectors.toList());
//
//        cucumbers.removeAll(matchedCucumbers);
//    }
//
//    public void eatAllThatAreBiggerThan(int size) {
//        List<Cucumber> matchedCucumbers = cucumbers.stream()
//            .filter(cucumber -> size > cucumber.getSize())
//            .collect(Collectors.toList());
//
//        cucumbers.removeAll(matchedCucumbers);
//    }
}
