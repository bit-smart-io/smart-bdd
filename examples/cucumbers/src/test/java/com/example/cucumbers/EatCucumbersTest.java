/*
 * Smart BDD - The smart way to do behavior-driven development.
 * Copyright (C)  2021  James Bayliss
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.example.cucumbers;

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
import static com.example.cucumbers.builders.UserGivenBuilder.iAm;

/**
 * Tutorial and example usage of builders
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EatCucumbersTest extends BaseTest {

    /**
     * TODO Work in progress for the feature documentation and notes. See README.md.
     */
    @Override
    public void doc() {
        featureNotes("Working progress for example of usage Smart BDD");
    }

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
     * When I request to eat cucumbers with quantity 1 with colour "red"
     * Then I should have cucumbers with quantity 0
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
        when(iRequestToEatCucumbers().withQuantity(1).withColour("red"));
        then(iShouldHaveCucumbers().withQuantity(0));
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
        when(iRequestToEatCucumbers().withQuantity(1).withColour("red"));
        then(iShouldHaveCucumbers().withQuantity(0));
    }

    /**
     * Incremental update. Notice the optional syntax sugar andACucumber()
     * <p>
     * Output:
     * Given I have a cucumber with colour "red" and a cucumber with colour "blue"
     * When I request to eat cucumbers with quantity 1 with colour "red"
     * Then I should have cucumbers with quantity 1
     */
    @Order(2)
    @Test
    void givenOneRedAndOneBlueCucumber_whenIEatOneRed_IhaveOneCucumberLeft() {
        given(iHave(aCucumber().withColour("red"), andACucumber().withColour("blue")));
        when(iRequestToEatCucumbers().withQuantity(1).withColour("red"));
        then(iShouldHaveCucumbers().withQuantity(1));
    }

    /**
     * Let's update the expected state to assert on colour.
     * <p>
     * withColour is a little ambitious. It really means all expected cucumbers to be blue.
     */
    @Order(3)
    @Test
    void givenOneRedAndOneBlueCucumber_whenIEatOneRed_IhaveOneBlueCucumberLeft() {
        given(iHave(aCucumber().withColour("red"), andACucumber().withColour("blue")));
        when(iRequestToEatCucumbers().withQuantity(1).withColour("red"));
        then(iShouldHaveCucumbers().withQuantity(1).withColour("blue"));
    }

    /**
     * Let's update the expected state to do an exact match
     */
    @Order(4)
    @Test
    void givenOneRedAndOneBlueCucumber_whenIEatOneRed_IhaveOneBlueCucumberLeft_betterAssertion() {
        given(iHave(aCucumber().withColour("red"), andACucumber().withColour("blue")));
        when(iRequestToEatCucumbers().withQuantity(1).withColour("red"));
        then(iShouldHave(aCucumber().withColour("blue")));
    }

    /**
     * Adding a new feature of not hungry
     * <p>
     * Create new given(UserGivenBuilder builder)
     * <pre>
     * With this new feature we are adding some notes. This shows how we can add to the report.
     * if (!given.isHungry()) {
     *   notes().text().add("Not hungry, so will not eat");
     * }
     * </pre>
     * The is
     */
    @Order(5)
    @Test
    void givenOneRedAndOneBlueCucumberAndNotHungry_whenIRequestToEatOneRed_IhaveOneRedAndOneBlueCucumberLeft() {
        given(iAm().notHungry());
        given(iHave(aCucumber().withColour("red"), andACucumber().withColour("blue")));
        when(iRequestToEatCucumbers().withQuantity(1).withColour("red"));
        then(iShouldHaveCucumbers().withQuantity(2));
    }

    /**
     * <pre>
     * Next tests:
     * - show how to add uml, maybe an interaction diagram.
     *  - notes().uml().add() Very useful for downstream dependencies.
     *  - Explore testCase().notes().uml(), testSuites().notes().uml()
     * - show async set actions. given body in an action.
     * - show how to add new wordify expressions. wordify().add(). Maybe
     * From: When I request to eat cucumbers with quantity 1 with colour "red"
     * To: When I request to eat 1 red cucumber
     * 1 and red need to be highlighted in the html test report
     * You need to specify verbs, nouns, subjects etc... and it may be possible
     * - show my_test(@Given int amount, @Given String colour).
     * </pre>
     */
    @Test
    void todo() {
        //TODO
    }

    /**
     * Idea:
     * - can you generate a better title for the test?
     * - or just wordify the title?
     * <p>
     * In this class, the method titles are same as the test wordify but condensed
     * <p>
     * For example
     * "Given user given builder I am not hungry
     * Given I have a cucumber with colour "red" and a cucumber with colour "blue"
     * When I request to eat cucumbers with quantity 1 with colour "red"
     * Then I should have cucumbers with quantity 2"
     * <p>
     * Has the title givenOneRedAndOneBlueCucumberAndNotHungry_whenIRequestToEatOneRed_IhaveOneRedAndOneBlueCucumberLeft
     * The title is kinda what the test should have been
     */
    @Test
    void generate_title_idea() {
        //TODO
    }
}
