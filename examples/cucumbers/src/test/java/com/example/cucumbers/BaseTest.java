package com.example.cucumbers;

import com.example.cucumbers.builders.CucumberBuilder;
import com.example.cucumbers.builders.CucumberGivenBuilder;
import com.example.cucumbers.builders.UserGivenBuilder;
import com.example.cucumbers.model.CucumberGiven;
import com.example.cucumbers.model.CucumberThen;
import com.example.cucumbers.model.CucumberWhen;
import com.example.cucumbers.model.UserGiven;
import io.bitsmart.bdd.report.junit5.annotations.InjectTestCaseResult;
import io.bitsmart.bdd.report.junit5.results.extension.ContextExtensionParameterResolver;
import io.bitsmart.bdd.report.junit5.results.extension.ReportExtension;
import io.bitsmart.bdd.report.junit5.results.model.TestCaseResult;
import io.bitsmart.bdd.report.junit5.results.model.notes.Notes;
import io.bitsmart.bdd.report.utils.ThenBuilder;
import io.bitsmart.bdd.report.utils.WhenBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith({ReportExtension.class, ContextExtensionParameterResolver.class})
public class BaseTest {

    private final CucumberService cucumberService = new CucumberService();
    private Notes notes;

    /**
     * Example of what can be injected in the before class.
     *
     * @param testCaseResult update the results with data such as request/response http headers and body.
     * @param testInfo basic test info
     * @param testReporter not hooked up to smart bdd yet
     */
    @BeforeEach
    void setUp(@InjectTestCaseResult TestCaseResult testCaseResult, TestInfo testInfo, TestReporter testReporter) {
        System.out.println("testCaseResult: " + testCaseResult);
        this.notes = testCaseResult.getNotes();
    }

    public void given(CucumberBuilder... cucumbers) {
        given(CucumberGivenBuilder.iHave(cucumbers));
    }

    public void given(UserGivenBuilder builder) {
        UserGiven given = builder.build();
        if (!given.isHungry()) {
            notes.text().add("Not hungry, so will not eat");
        }
        cucumberService.setHungry(given.isHungry());
    }

    public void given(CucumberGivenBuilder builder) {
        CucumberGiven cucumberGiven = builder.build();
        cucumberService.setCucumbers(cucumberGiven.getCucumbers());
    }

    public void when(WhenBuilder<CucumberWhen> builder) {
        CucumberWhen cucumberWhen = builder.build();
        cucumberService.eat(cucumberWhen.getQuantity(), cucumberWhen.getColour());
    }

    public void then(ThenBuilder<CucumberThen> builder) {
        CucumberThen cucumberThen = builder.build();

        cucumberThen.getColour().ifPresent(colour -> cucumberService.getCucumbers()
            .forEach((cucumber -> assertThat(cucumber.getColour()).isEqualTo(colour))));
        cucumberThen.getQuantity().ifPresent(quantity -> assertThat(cucumberService.getCucumbers().size()).isEqualTo(quantity));
        cucumberThen.getCucumbers().ifPresent(cucumbers -> assertThat(cucumberService.getCucumbers().containsAll(cucumbers)));
    }
}
