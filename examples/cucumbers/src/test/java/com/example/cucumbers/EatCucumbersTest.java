package com.example.cucumbers;

import com.example.cucumbers.builders.UserGivenBuilder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static com.example.cucumbers.builders.CucumberBuilder.aCucumber;
import static com.example.cucumbers.builders.CucumberBuilder.andACucumber;
import static com.example.cucumbers.builders.CucumberGivenBuilder.iHave;
import static com.example.cucumbers.builders.CucumberThenBuilder.iShouldHave;
import static com.example.cucumbers.builders.CucumberThenBuilder.iShouldHaveCucumbers;
import static com.example.cucumbers.builders.CucumberWhenBuilder.iRequestToEatCucumbers;

/**
 * Tutorial and example usage of builders
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EatCucumbersTest extends BaseTest {

    /**
     * First test shows the basic building blocks of using builders.
     * Builders are not mandatory - if you have a non-trivial app to test, using builders is your best option!
     * <p>
     * - given() accepts a GivenBuilder - setup state for the scenario.
     * - when() accepts a WhenBuilder - the request state for the scenario.
     * - then() accepts a ThenBuilder - the expected state for the scenario.
     * <p>
     * Output:
     * Given I have a cucumber with colour "red"
     * When I request to eat cucumbers with amount 1 with colour "red"
     * Then I should have cucumbers with amount 0
     * <p>
     * If you follow the naming convention you should construct an API that is easy to read, use and maintain.
     * - iHave() is syntax sugar for CucumberGivenBuilder - setup state
     * - iRequestToEatCucumbers() is syntax sugar for CucumberWhenBuilder - request state
     * - iShouldHaveCucumbers() is syntax sugar for CucumberThenBuilder - expected state
     * <p>
     * Try not to be tempted to do use factory methods below are ant-patterns:
     * - iHave(5, "red")
     * - iEatCucumbers(5, "red")
     * - iEatCucumbers(5, redCucumber())
     * In the long run they will be limiting.
     * <p>
     * Starting to test any project and thinking about the nouns, verbs and or the bounded context is hard.
     * The boilerplate code can be a little tricky.
     */
    @Order(0)
    @Test
    void givenOneRedCucumber_whenIEatOneRed_IHaveNoneLeft() {
        given(iHave(aCucumber().withColour("red")));
        when(iRequestToEatCucumbers().withAmount(1).withColour("red"));
        then(iShouldHaveCucumbers().withAmount(0));
    }

    /**
     * For this given() you can technically omit iHave() and pass in CucumberBuilders to build the state.
     * For when() and then() makes no sense to omit the When and Given Builders.
     * <p>
     * Original Output: Given I have a cucumber with colour "red"
     * Shorter  Output: Given a cucumber with colour "red"
     * <p>
     * This shorter version lacks the context of who has the Cucumbers. But is nicer when writing the test to omit iHave().
     */
    @Order(1)
    @Test
    void givenOneRedCucumber_whenIEatOneRed_IHaveNoneLeft_omittingIHave() {
        given(aCucumber().withColour("red"));
        when(iRequestToEatCucumbers().withAmount(1).withColour("red"));
        then(iShouldHaveCucumbers().withAmount(0));
    }

    /**
     * Incremental update. Notice the optional syntax sugar andACucumber()
     *
     * Output:
     * Given I have a cucumber with colour "red" and a cucumber with colour "blue"
     * When I request to eat cucumbers with amount 1 with colour "red"
     * Then I should have cucumbers with amount 1
     */
    @Order(2)
    @Test
    void givenOneRedAndOneBlueCucumber_whenIEatOneRed_IhaveOneCucumberLeft() {
        given(iHave(aCucumber().withColour("red"), andACucumber().withColour("blue")));
        when(iRequestToEatCucumbers().withAmount(1).withColour("red"));
        then(iShouldHaveCucumbers().withAmount(1));
    }

    /**
     * Let's update the expected state to assert on colour.
     *
     * withColour is a little ambitious. It really means all expected cucumbers to be blue.
     */
    @Order(3)
    @Test
    void givenOneRedAndOneBlueCucumber_whenIEatOneRed_IhaveOneBlueCucumberLeft() {
        given(iHave(aCucumber().withColour("red"), andACucumber().withColour("blue")));
        when(iRequestToEatCucumbers().withAmount(1).withColour("red"));
        then(iShouldHaveCucumbers().withAmount(1).withColour("blue"));
    }

    /**
     * Let's update the expected state to do an exact match
     */
    @Order(4)
    @Test
    void givenOneRedAndOneBlueCucumber_whenIEatOneRed_IhaveOneBlueCucumberLeft_betterAssertion() {
        given(iHave(aCucumber().withColour("red"), andACucumber().withColour("blue")));
        when(iRequestToEatCucumbers().withAmount(1).withColour("red"));
        then(iShouldHave(aCucumber().withColour("blue")));
    }

    /**
     * Adding a new feature of not hungry
     *
     * Create new given(UserGivenBuilder builder)
     */
    @Order(5)
    @Test
    void givenOneRedAndOneBlueCucumberAndNotHungry_whenIRequestToEatOneRed_IhaveOneRedAndOneBlueCucumberLeft() {
        given(UserGivenBuilder.iAm().notHungry());
        given(iHave(aCucumber().withColour("red"), andACucumber().withColour("blue")));
        when(iRequestToEatCucumbers().withAmount(1).withColour("red"));
        then(iShouldHaveCucumbers().withAmount(2));
    }
}
