package ft.bdd.examples.cucumber.builder;

import junit5.results.extension.ReportExtension;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

import static ft.bdd.examples.cucumber.builder.builders.CucumberGivenBuilder.iHaveCucumbers;
import static ft.bdd.examples.cucumber.builder.builders.CucumberThenBuilder.iShouldHaveCucumbers;
import static ft.bdd.examples.cucumber.builder.builders.CucumberWhenBuilder.iEatCucumbers;

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
@ExtendWith(ReportExtension.class)

//TODO what happens when the base class has an extension???
public class CucumberComparisonTest extends BaseTest {

    @Order(0)
    @Test
    void eat5OutOf12() {
        given(iHaveCucumbers().withAmount(12));
        when(iEatCucumbers().withAmount(5));
        then(iShouldHaveCucumbers().withAmount(7));
    }

    /**
     * harder to maintain because all builders have to chain of the given builder
     * easier to write tests after the investment of writing and maintain the given builder
     * but when would you action building and assertions?
     @Order(1)
     @Test
     void eat5OutOf12() {
        given().cucumbers().withAmount(12))
        when().iEat().cucumbers().withAmount(5))
        then().iShouldHave().cucumbers().withAmount(7))
     }*/
}
