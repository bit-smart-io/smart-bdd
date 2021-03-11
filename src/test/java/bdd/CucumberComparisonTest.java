package bdd;

import junit5.extension.testwatcher.results.ResultsExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * // https://cucumber.io/docs/gherkin/reference/
 * Scenario: eat 5 out of 12
 * Given there are 12 cucumbers
 * When I eat 5 cucumbers
 * Then I should have 7 cucumbers
 *
 * Scenario: eat 5 out of 20
 * Given there are 20 cucumbers
 * When I eat 5 cucumbers
 * Then I should have 15 cucumbers
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
@ExtendWith(ResultsExtension.class)
public class CucumberComparisonTest {
    private CucumberService cucumberService;

    @BeforeEach
    private void reset() {
        cucumberService = new CucumberService();
    }

    @Order(0)
    @Test
    void eat5OutOf12() {
        givenThereAreCucumbers(12);
        whenIEatCucumbers(5);
        thenIShouldHaveCucumbers(7);
    }

    @Order(1)
    @Test
    void eat5OutOf12UsingBuilder() {
        // better as it takes a builder
        // but you have to locate all the builders
        // given(cucumbers().withAmount(12))
        // when(iEat().cucumbers().withAmount(5))
        // then(iShouldHave().cucumbers().withAmount(7))

        // harder to maintain because all builders have to chain of the given builder
        // easier to write tests after the investment of writing and maintain the given builder
        // given().cucumbers().withAmount(12))
        // when().iEat().cucumbers().withAmount(5))
        // then().iShouldHave().cucumbers().withAmount(7))
    }

    private void givenThereAreCucumbers(int number) {
        cucumberService.setNumberOfCucumbers(number);
    }

    private void whenIEatCucumbers(int number) {
        cucumberService.eat(number);
    }

    private void  thenIShouldHaveCucumbers(int number) {
        assertThat(cucumberService.getNumberOfCucumbers()).isEqualTo(number);
    }

    class CucumberService {
        private int numberOfCucumbers;

        public int getNumberOfCucumbers() {
            return numberOfCucumbers;
        }

        public void setNumberOfCucumbers(int number) {
            this.numberOfCucumbers = number;
        }

        public void eat(int number) {
            numberOfCucumbers -= number;
        }
    }
}
