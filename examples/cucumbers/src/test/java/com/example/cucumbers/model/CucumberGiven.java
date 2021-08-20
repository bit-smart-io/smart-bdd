package com.example.cucumbers.model;

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
