package com.example.cucumbers;

import com.example.cucumbers.model.Cucumber;
import com.example.cucumbers.model.User;

import java.util.ArrayList;
import java.util.List;

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
            // cucumbers don't have identity - temp code
            for (int i = 0; i < amount; i++) {
                if (cucumbers.get(i).getColour().equals(colour)) {
                    cucumbers.remove(i);
                }
            }
        }
    }

//    /**  this will not work as cucumbers don't have identity */
//    public void eat(int amount, String colour) {
//        if (user.isHungry()) {
//
//            List<Cucumber> matchedCucumbers = cucumbers.stream()
//                .filter(cucumber -> colour.equals(cucumber.getColour()))
//                .limit(amount)
//                .collect(Collectors.toList());
//            cucumbers.removeAll(matchedCucumbers);
//        }
//    }
//
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
