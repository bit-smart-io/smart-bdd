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

import com.example.cucumbers.builders.CucumberBuilder;
import com.example.cucumbers.builders.CucumberGivenBuilder;
import com.example.cucumbers.builders.UserGivenBuilder;
import com.example.cucumbers.model.CucumberGiven;
import com.example.cucumbers.model.CucumberThen;
import com.example.cucumbers.model.CucumberWhen;
import com.example.cucumbers.model.UserGiven;
import io.bitsmart.bdd.report.junit5.annotations.InjectTestCaseResult;
import io.bitsmart.bdd.report.junit5.annotations.InjectTestSuiteResult;
import io.bitsmart.bdd.report.junit5.results.extension.ReportExtension;
import io.bitsmart.bdd.report.junit5.results.extension.TestSuiteResultParameterResolver;
import io.bitsmart.bdd.report.junit5.results.model.TestCaseResult;
import io.bitsmart.bdd.report.junit5.results.model.TestSuiteResult;
import io.bitsmart.bdd.report.junit5.results.model.notes.Notes;
import io.bitsmart.bdd.report.utils.ThenBuilder;
import io.bitsmart.bdd.report.utils.WhenBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith({ReportExtension.class, TestSuiteResultParameterResolver.class})
public abstract class BaseTest {

    private final CucumberService cucumberService = new CucumberService();
    private Notes notes;
    private Notes testSuiteNotes;

    /**
     * Example of what can be injected in the before class.
     *
     * @param testCaseResult update the results with data such as request/response http headers and body.
     * @param testInfo basic test info
     * @param testReporter not hooked up to smart bdd yet
     */
    @BeforeEach
    void setUp(
        @InjectTestCaseResult TestCaseResult testCaseResult,
        @InjectTestSuiteResult TestSuiteResult testSuiteResult,
        TestInfo testInfo,
        TestReporter testReporter)
    {
        //System.out.println("testCaseResult: " + testCaseResult);

        //TODO work out the api for this see README.md
        this.notes = testCaseResult.getNotes();
        this.testSuiteNotes = testSuiteResult.getNotes();
        doc();
    }

    public abstract void doc();

    public void featureNotes(String notes) {
        if (testSuiteNotes().text().getNotes().size() == 0) {
            testSuiteNotes().text().add(notes);
        }
    }

    public Notes testSuiteNotes() {
        return testSuiteNotes;
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
