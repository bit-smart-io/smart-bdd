package component.examples.cucumber.verysimple.builder;

import component.examples.cucumber.verysimple.SimpleCucumberService;
import component.examples.cucumber.verysimple.builder.domain.SimpleCucumberGiven;
import component.examples.cucumber.verysimple.builder.domain.SimpleCucumberThen;
import component.examples.cucumber.verysimple.builder.domain.SimpleCucumberWhen;
import io.bitsmart.bdd.report.utils.GivenBuilder;
import io.bitsmart.bdd.report.utils.ThenBuilder;
import io.bitsmart.bdd.report.utils.WhenBuilder;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleBaseTest {
    SimpleCucumberService simpleCucumberService = new SimpleCucumberService();

    public void given(GivenBuilder<SimpleCucumberGiven> builder) {
        SimpleCucumberGiven cucumberGiven = builder.build();
        simpleCucumberService.setNumberOfCucumbers(cucumberGiven.getAmount());
    }

    public void when(WhenBuilder<SimpleCucumberWhen> builder) {
        SimpleCucumberWhen cucumberWhen = builder.build();
        simpleCucumberService.eat(cucumberWhen.getAmount());
    }

    public void then(ThenBuilder<SimpleCucumberThen> builder) {
        SimpleCucumberThen cucumberThen = builder.build();
        assertThat(simpleCucumberService.getNumberOfCucumbers()).isEqualTo(cucumberThen.getAmount());
    }
}
