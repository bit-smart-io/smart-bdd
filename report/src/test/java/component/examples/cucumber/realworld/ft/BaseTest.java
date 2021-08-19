package component.examples.cucumber.realworld.ft;

import component.examples.cucumber.realworld.app.CucumberService;
import component.examples.cucumber.realworld.ft.builders.CucumberBuilder;
import component.examples.cucumber.realworld.ft.builders.CucumberGivenBuilder;
import component.examples.cucumber.realworld.ft.builders.UserGivenBuilder;
import component.examples.cucumber.realworld.ft.domain.CucumberGiven;
import component.examples.cucumber.realworld.ft.domain.CucumberThen;
import component.examples.cucumber.realworld.ft.domain.CucumberWhen;
import component.examples.cucumber.realworld.ft.domain.UserGiven;
import io.bitsmart.bdd.report.utils.GivenBuilder;
import io.bitsmart.bdd.report.utils.ThenBuilder;
import io.bitsmart.bdd.report.utils.WhenBuilder;

import static org.assertj.core.api.Assertions.assertThat;

public class BaseTest {
    CucumberService cucumberService = new CucumberService();

    public void given(CucumberBuilder... cucumbers) {
        given(CucumberGivenBuilder.iHave(cucumbers));
    }

    public void given(UserGivenBuilder builder) {
        UserGiven given = builder.build();
        cucumberService.setHungry(given.isHungry());
    }

    public void given(CucumberGivenBuilder builder) {
        CucumberGiven cucumberGiven = builder.build();
        cucumberService.setCucumbers(cucumberGiven.getCucumbers());
    }


    public void when(WhenBuilder<CucumberWhen> builder) {
        CucumberWhen cucumberWhen = builder.build();
        cucumberService.eat(cucumberWhen.getAmount(), cucumberWhen.getColour());
    }

    public void then(ThenBuilder<CucumberThen> builder) {
        CucumberThen cucumberThen = builder.build();

        cucumberThen.getColour().ifPresent(colour -> cucumberService.getCucumbers()
            .forEach((cucumber -> assertThat(cucumber.getColour()).isEqualTo(colour))));
        cucumberThen.getAmount().ifPresent(amount -> assertThat(cucumberService.getCucumbers().size()).isEqualTo(amount));
        cucumberThen.getCucumbers().ifPresent(cucumbers -> assertThat(cucumberService.getCucumbers().containsAll(cucumbers)));
    }

}
