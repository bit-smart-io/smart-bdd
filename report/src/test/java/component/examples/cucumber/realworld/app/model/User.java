package component.examples.cucumber.realworld.app.model;

import java.util.Objects;

public class User {
    private boolean isHungry;

    public User(boolean isHungry) {
        this.isHungry = isHungry;
    }

    public void setHungry(boolean hungry) {
        isHungry = hungry;
    }

    public boolean isHungry() {
        return isHungry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return isHungry == user.isHungry;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isHungry);
    }

    @Override
    public String toString() {
        return "User{" +
            "isHungry=" + isHungry +
            '}';
    }
}
