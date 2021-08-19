package component.examples.cucumber.realworld.ft.builders;

import component.examples.cucumber.realworld.ft.domain.UserGiven;
import io.bitsmart.bdd.report.utils.Builder;

public final class UserGivenBuilder implements Builder<UserGiven>  {
    private boolean isHungry;

    private UserGivenBuilder() {
    }

    public static UserGivenBuilder iAm() {
        return new UserGivenBuilder();
    }

    public UserGivenBuilder notHungry() {
        this.isHungry = false;
        return this;
    }

    public UserGivenBuilder withIsHungry(boolean isHungry) {
        this.isHungry = isHungry;
        return this;
    }

    public UserGiven build() {
        return new UserGiven(isHungry);
    }
}
