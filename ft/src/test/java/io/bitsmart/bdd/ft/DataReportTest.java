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

import io.bitsmart.bdd.ft.report.ports.json.builders.TestSuiteNameToFileBuilder;
import io.bitsmart.bdd.ft.report.ports.json.builders.TestSuiteSummaryBuilder;
import io.bitsmart.bdd.ft.report.ports.json.model.DataReportIndex;
import io.bitsmart.bdd.ft.report.ports.json.model.Status;
import io.bitsmart.bdd.ft.report.ports.json.model.TestSuite;
import io.bitsmart.bdd.report.junit5.launcher.TestLauncher;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static io.bitsmart.bdd.ft.report.ports.json.builders.TestCaseBuilder.aTestCase;
import static io.bitsmart.bdd.ft.report.ports.json.builders.TestSuiteLinksBuilder.aTestSuiteLinks;
import static io.bitsmart.bdd.ft.report.ports.json.builders.TestSuiteSummaryBuilder.aTestSuiteSummary;
import static io.bitsmart.bdd.ft.report.ports.utils.DataReportTestUtils.loadReportIndex;
import static io.bitsmart.bdd.ft.report.ports.utils.DataReportTestUtils.loadTestSuite;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Due to the static nature of ReportExtension, this class can't be annotated with ReportExtension.
 *
 * TODO address the below
 * SmartTestExecutionListener will write the index and test suites files twice as it will also listen to this as a test.
 * There's no point adding logic to SmartTestExecutionListener as this is specific to how it is run here.
 * Else we could check a tag and or annotation maybe?
 * */
public class DataReportTest {

    // TODO
    // create the reports once
    // test html report
    // html report to summarise failures. DO we want a table
    // still need a project where competitors reports are stored

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
        DataReportIndex reportIndex = loadReportIndex();
        assertThat(reportIndex.getLinks()).isEqualTo(aTestSuiteLinks().withTestSuites(singletonList(classUnderTestTestSuiteNameToFileBuilder())).build());
        assertThat(reportIndex.getSummary()).isEqualTo(classUnderTestTestSuiteSummaryBuilder().build());
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
    void generatesTestSuiteJson() {
        assertThat(testSuite).isNotNull();
        assertThat(testSuite.getTitle()).isEqualTo("Class under test");
        assertThat(testSuite.getName()).isEqualTo("io.bitsmart.bdd.ft.ClassUnderTest");
        assertThat(testSuite.getClassName()).isEqualTo("ClassUnderTest");
        assertThat(testSuite.getPackageName()).isEqualTo("io.bitsmart.bdd.ft");
        assertThat(testSuite.getMethodNames()).containsExactly("testMethod", "paramTest", "paramTest", "paramTest");
        assertThat(testSuite.getSummary()).isEqualTo(classUnderTestTestSuiteSummaryBuilder().build());
    }

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

    public static TestSuiteSummaryBuilder classUnderTestTestSuiteSummaryBuilder() {
        return aTestSuiteSummary().withTestCase(4).withPassed(4).withSkipped(0).withFailed(0).withAborted(0);
    }

    public static TestSuiteNameToFileBuilder classUnderTestTestSuiteNameToFileBuilder() {
        return TestSuiteNameToFileBuilder.aTestSuiteNameToFile()
            .withName("io.bitsmart.bdd.ft.ClassUnderTest")
            .withFile("TEST-io.bitsmart.bdd.ft.ClassUnderTest.json");
    }
}
