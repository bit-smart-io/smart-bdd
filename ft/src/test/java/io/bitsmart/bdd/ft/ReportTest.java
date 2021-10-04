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

package io.bitsmart.bdd.ft;

import io.bitsmart.bdd.ft.report.ports.json.model.ReportIndex;
import io.bitsmart.bdd.ft.report.ports.json.model.Status;
import io.bitsmart.bdd.ft.report.ports.json.model.TestSuite;
import io.bitsmart.bdd.report.junit5.launcher.TestLauncher;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static io.bitsmart.bdd.ft.report.ports.json.builders.TestCaseBuilder.aTestCase;
import static io.bitsmart.bdd.ft.report.ports.json.builders.TestSuiteSummaryBuilder.aTestSuiteSummary;
import static io.bitsmart.bdd.ft.report.ports.utils.ReportTestUtils.loadReportIndex;
import static io.bitsmart.bdd.ft.report.ports.utils.ReportTestUtils.loadTestSuite;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Due to the static nature of ReportExtension, this class can't be annotated with ReportExtension
 * */
public class ReportTest {

    private static TestSuite testSuite;

    @BeforeAll
    static void beforeAll() throws IOException {
        TestLauncher.launch(ClassUnderTest.class);
        testSuite = loadTestSuite(ClassUnderTest.class);
    }

    /**
     * {
     *   "links" : {
     *     "testSuites" : [ {
     *       "name" : "io.bitsmart.bdd.ft.ClassUnderTest",
     *       "file" : "TEST-io.bitsmart.bdd.ft.ClassUnderTest.json"
     *     } ]
     *   },
     *   "summary" : {
     *     "passed" : 4,
     *     "skipped" : 0,
     *     "failed" : 0,
     *     "aborted" : 0,
     *     "tests" : 4
     *   }
     * }
     * @throws IOException
     */
    @Test
    void generatesIndexJson() throws IOException {
        ReportIndex reportIndex = loadReportIndex();
        assertThat(reportIndex).isNotNull();
    }

    @Test
    void generatesTestSuiteJson() {
        assertThat(testSuite).isNotNull();
        assertThat(testSuite.getName()).isEqualTo("io.bitsmart.bdd.ft.ClassUnderTest");
        assertThat(testSuite.getClassName()).isEqualTo("ClassUnderTest");
        assertThat(testSuite.getPackageName()).isEqualTo("io.bitsmart.bdd.ft");
        assertThat(testSuite.getMethodNames()).containsExactly("testMethod", "paramTest", "paramTest", "paramTest");
        assertThat(testSuite.getSummary()).isEqualTo(aTestSuiteSummary().withTestCase(4).withPassed(4).withSkipped(0).withFailed(0).withAborted(0).build());
    }

    /**
     * {
     *   "title" : "Class under test",
     *   "name" : "io.bitsmart.bdd.ft.ClassUnderTest",
     *   "className" : "ClassUnderTest",
     *   "packageName" : "io.bitsmart.bdd.ft",
     *   "methodNames" : [ "testMethod", "paramTest", "paramTest", "paramTest" ],
     *   "summary" : {
     *     "passed" : 4,
     *     "skipped" : 0,
     *     "failed" : 0,
     *     "aborted" : 0,
     *     "tests" : 4
     *   },
     *   "testCases" : [ {
     *     "wordify" : "Passing assertion",
     *     "status" : "PASSED",
     *     "name" : "testMethod",
     *     "methodName" : "testMethod",
     *     "className" : "ClassUnderTest",
     *     "packageName" : "io.bitsmart.bdd.ft"
     *   }, {
     *     "wordify" : "Passing assertion with value 1",
     *     "status" : "PASSED",
     *     "name" : "paramTest value 1",
     *     "methodName" : "paramTest",
     *     "className" : "ClassUnderTest",
     *     "packageName" : "io.bitsmart.bdd.ft"
     *   }, {
     *     "wordify" : "Passing assertion with value 2",
     *     "status" : "PASSED",
     *     "name" : "paramTest value 2",
     *     "methodName" : "paramTest",
     *     "className" : "ClassUnderTest",
     *     "packageName" : "io.bitsmart.bdd.ft"
     *   }, {
     *     "wordify" : "Passing assertion with value 3",
     *     "status" : "PASSED",
     *     "name" : "paramTest value 3",
     *     "methodName" : "paramTest",
     *     "className" : "ClassUnderTest",
     *     "packageName" : "io.bitsmart.bdd.ft"
     *   } ]
     * }
     */
    @Test
    void generatesTestCaseJsonForTestMethod() {
        assertThat(testSuite.getTestCases()).contains(
            aTestCase()
                .withClassName("ClassUnderTest")
                .withPackageName("io.bitsmart.bdd.ft")
                .withMethodName("testMethod")
                .withName("testMethod")
                .withStatus(Status.PASSED)
                .withWordify("Passing assertion")
                .build());
    }
}
