package component.examples.cucumber.verysimple.withoutbuilder;

import component.examples.cucumber.verysimple.SimpleCucumberService;
import io.bitsmart.bdd.report.junit5.results.extension.ReportExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

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
public class SimpleCucumberWithoutBuildersTest {
    private SimpleCucumberService simpleCucumberService;

    @BeforeEach
    private void reset() {
        simpleCucumberService = new SimpleCucumberService();
    }

    @Order(0)
    @Test
    void eat5OutOf12() {
        givenThereAreCucumbers(12);
        whenIEatCucumbers(5);
        thenIShouldHaveCucumbers(7);
    }

    @Order(1)
    @ParameterizedTest(name = "#{index} - Eat {1} out of {0}")
    @CsvSource({"12,5,7", "20,5,15"})
    void eat(int start, int eat, int left) {
        givenThereAreCucumbers(start);
        whenIEatCucumbers(eat);
        thenIShouldHaveCucumbers(left);
    }

    private void givenThereAreCucumbers(int number) {
        simpleCucumberService.setNumberOfCucumbers(number);
    }

    private void whenIEatCucumbers(int number) {
        simpleCucumberService.eat(number);
    }

    private void  thenIShouldHaveCucumbers(int number) {
        assertThat(simpleCucumberService.getNumberOfCucumbers()).isEqualTo(number);
    }
}
