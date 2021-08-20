package com.example.cucumbers.model;

public class UserGiven {
    private final boolean isHungry;

    public UserGiven(boolean isHungry) {
        this.isHungry = isHungry;
    }

    public boolean isHungry() {
        return isHungry;
    }
}
