package bdd.cucumber.builder;

import bdd.cucumber.CucumberService;
import bdd.cucumber.builder.domain.CucumberGiven;
import bdd.cucumber.builder.domain.CucumberThen;
import bdd.cucumber.builder.domain.CucumberWhen;
import bdd.cucumber.builder.framework.GivenBuilder;
import bdd.cucumber.builder.framework.ThenBuilder;
import bdd.cucumber.builder.framework.WhenBuilder;

import static org.assertj.core.api.Assertions.assertThat;

public class BaseTest {
    CucumberService cucumberService = new CucumberService();

    public void given(GivenBuilder<CucumberGiven> builder) {
        CucumberGiven cucumberGiven = builder.build();
        cucumberService.setNumberOfCucumbers(cucumberGiven.getAmount());
    }

    public void when(WhenBuilder<CucumberWhen> builder) {
        CucumberWhen cucumberWhen = builder.build();
        cucumberService.eat(cucumberWhen.getAmount());
    }

    public void then(ThenBuilder<CucumberThen> builder) {
        CucumberThen cucumberThen = builder.build();
        assertThat(cucumberService.getNumberOfCucumbers()).isEqualTo(cucumberThen.getAmount());
    }
}
