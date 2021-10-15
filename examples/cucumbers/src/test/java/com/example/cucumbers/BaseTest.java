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

import io.bitsmart.bdd.report.junit5.annotations.InjectTestCaseResult;
import io.bitsmart.bdd.report.junit5.annotations.InjectTestSuiteResult;
import io.bitsmart.bdd.report.junit5.results.extension.ReportExtension;
import io.bitsmart.bdd.report.junit5.results.extension.TestSuiteResultParameterResolver;
import io.bitsmart.bdd.report.junit5.results.model.TestCaseResult;
import io.bitsmart.bdd.report.junit5.results.model.TestSuiteResult;
import io.bitsmart.bdd.report.junit5.results.model.notes.Notes;
import io.bitsmart.bdd.report.mermaid.SequenceDiagram;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith({ReportExtension.class, TestSuiteResultParameterResolver.class})
public abstract class BaseTest {

    Context context;

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
        context = new Context(testCaseResult, testSuiteResult, testInfo, testReporter);
        doc();
    }

    public static class Context {
        private final Internal internal;
        private final Feature feature;
        private final CurrentTest test;

        public Context(TestCaseResult testCaseResult, TestSuiteResult testSuiteResult, TestInfo testInfo, TestReporter testReporter) {
            feature = new Feature(testSuiteResult.getNotes());
            test = new CurrentTest(testCaseResult.getNotes());
            internal = new Internal(testCaseResult, testSuiteResult, testInfo, testReporter);
        }

        public Feature feature() {
            return feature;
        }

        public CurrentTest test() {
            return test;
        }

        // feature().doc().text() or feature().doc().uml()
        // test().doc() or just text(), diagram(). diagram().createAnother("id")
        // internal() access to internal stuff
        // could create a context
        // context.notes().textOnce()
        // context().testSuite()
        // context().testCase()
        // context().testInfo()
        // context().testReporter()
        // testSuite().addNote()
        // testCase().addNote()
    }

    public static class Internal {
        private final TestCaseResult testCaseResult;
        private final TestSuiteResult testSuiteResult;
        private final TestInfo testInfo;
        private final TestReporter testReporter;

        public Internal(TestCaseResult testCaseResult, TestSuiteResult testSuiteResult, TestInfo testInfo, TestReporter testReporter) {
            this.testCaseResult = testCaseResult;
            this.testSuiteResult = testSuiteResult;
            this.testInfo = testInfo;
            this.testReporter = testReporter;
        }
    }

    public static class Feature {
        private final Notes notes;

        public Feature(Notes notes) {
            this.notes = notes;
        }

        public Notes notes() {
            return notes;
        }
    }

    public static class CurrentTest {
        private final Notes notes;

        public CurrentTest(Notes notes) {
            this.notes = notes;
        }

        public Notes notes() {
            return notes;
        }
    }

    public abstract void doc();

    // addOnce(), then this logic doesn't have to be here
    public void featureNotes(String notes) {
        if (featureNotes().text().getNotes().size() == 0) {
            featureNotes().text().add(notes);
        }
    }

    public Notes notes() {
        return context.test().notes();
    }

    public SequenceDiagram sequenceDiagram() {
        return context.test().notes().diagram().get(0);
    }

    // would you need a helper method?
    public Notes featureNotes() {
        return context.feature().notes;
    }
}
