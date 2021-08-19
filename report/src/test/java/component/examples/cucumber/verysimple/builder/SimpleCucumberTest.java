package component.examples.cucumber.verysimple.builder;

import io.bitsmart.bdd.report.junit5.results.extension.ReportExtension;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

import static component.examples.cucumber.verysimple.builder.builders.SimpleCucumberGivenBuilder.iHaveCucumbers;
import static component.examples.cucumber.verysimple.builder.builders.SimpleCucumberThenBuilder.iShouldHaveCucumbers;
import static component.examples.cucumber.verysimple.builder.builders.SimpleCucumberWhenBuilder.iEatCucumbers;

/**
 * Example to show without using any builder api.
 *
 * Please see adjacent launcher test for the output.
 *
 * Below is an extract from https://cucumber.io/docs/gherkin/reference/
 * Scenario: eat 5 out of 12
 * Given there are 12 cucumbers
 * When I eat 5 cucumbers
 * Then I should have 7 cucumbers
 *
 * Scenario Outline: eating
 * Given there are <start> cucumbers
 * When I eat <eat> cucumbers
 * Then I should have <left> cucumbers
 * Examples:
 * | start | eat | left |
 * |    12 |   5 |    7 |
 * |    20 |   5 |   15 |
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(ReportExtension.class)
public class SimpleCucumberTest extends SimpleBaseTest {

    @Order(0)
    @Test
    void eat5OutOf12() {
        given(iHaveCucumbers().withAmount(12));
        when(iEatCucumbers().withAmount(5));
        then(iShouldHaveCucumbers().withAmount(7));
    }
}
