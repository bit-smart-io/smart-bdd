package ft.bdd.examples.cucumber.builder;

import ft.bdd.examples.cucumber.CucumberService;
import ft.bdd.examples.cucumber.builder.domain.CucumberGiven;
import ft.bdd.examples.cucumber.builder.domain.CucumberThen;
import ft.bdd.examples.cucumber.builder.domain.CucumberWhen;
import io.bitsmart.bdd.report.utils.GivenBuilder;
import io.bitsmart.bdd.report.utils.ThenBuilder;
import io.bitsmart.bdd.report.utils.WhenBuilder;

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
