package component.examples.cucumber.supersimple.builder;

import component.examples.cucumber.supersimple.SuperSimpleCucumberService;
import component.examples.cucumber.supersimple.builder.domain.CucumberGiven;
import component.examples.cucumber.supersimple.builder.domain.CucumberThen;
import component.examples.cucumber.supersimple.builder.domain.CucumberWhen;
import io.bitsmart.bdd.report.utils.GivenBuilder;
import io.bitsmart.bdd.report.utils.ThenBuilder;
import io.bitsmart.bdd.report.utils.WhenBuilder;

import static org.assertj.core.api.Assertions.assertThat;

public class BaseTest {
    SuperSimpleCucumberService superSimpleCucumberService = new SuperSimpleCucumberService();

    public void given(GivenBuilder<CucumberGiven> builder) {
        CucumberGiven cucumberGiven = builder.build();
        superSimpleCucumberService.setNumberOfCucumbers(cucumberGiven.getAmount());
    }

    public void when(WhenBuilder<CucumberWhen> builder) {
        CucumberWhen cucumberWhen = builder.build();
        superSimpleCucumberService.eat(cucumberWhen.getAmount());
    }

    public void then(ThenBuilder<CucumberThen> builder) {
        CucumberThen cucumberThen = builder.build();
        assertThat(superSimpleCucumberService.getNumberOfCucumbers()).isEqualTo(cucumberThen.getAmount());
    }
}
