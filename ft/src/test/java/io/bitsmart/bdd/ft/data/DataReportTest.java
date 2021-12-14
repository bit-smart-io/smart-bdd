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

package io.bitsmart.bdd.ft.data;

import io.bitsmart.bdd.ft.AbstractResultsForTestSuite;
import io.bitsmart.bdd.ft.report.infrastructure.json.builders.TestCaseBuilder;
import io.bitsmart.bdd.ft.report.infrastructure.json.model.TestCase;
import io.bitsmart.bdd.ft.undertest.basic.ClassUnderTest;
import org.junit.jupiter.api.Test;

import static io.bitsmart.bdd.ft.report.infrastructure.json.builders.TestCaseBuilder.aTestCase;
import static io.bitsmart.bdd.ft.report.infrastructure.json.builders.TestSuiteNameToFileBuilder.aTestSuiteNameToFile;
import static io.bitsmart.bdd.ft.report.infrastructure.json.builders.TestSuiteSummaryBuilder.aTestSuiteSummary;
import static io.bitsmart.bdd.ft.report.infrastructure.json.model.Status.PASSED;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Notes:
 * Due to the static nature of ReportExtension, this class can't be annotated with ReportExtension.
 * <p>
 * TODO address the below
 * SmartTestExecutionListener will write the index and test suites files twice as it will also listen to this as a test.
 * There's no point adding logic to SmartTestExecutionListener as this is specific to how it is run here.
 * Else we could check a tag and or annotation maybe?
 */
public class DataReportTest extends AbstractResultsForTestSuite {

    @Override
    public Class<?> classUnderTest() {
        return ClassUnderTest.class;
    }

    /**
     * <pre>
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
     * </pre>
     */
    @Test
    void generatesIndexJson() {
        assertIndexLinks(aTestSuiteNameToFile()
            .withName("io.bitsmart.bdd.ft.undertest.basic.ClassUnderTest")
            .withFile("TEST-io.bitsmart.bdd.ft.undertest.basic.ClassUnderTest.json"));
        assertIndexSummary(aTestSuiteSummary()
            .withTestCase(6)
            .withPassed(6)
            .withSkipped(0)
            .withFailed(0)
            .withAborted(0));
        assertIndexTimeStamp();
    }

    /**
     * <pre>
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
     * </pre>
     */
    @Test
    void verifyResultsForPassingTestCases() {
        assertTestSuitClass(testSuiteResult(), classUnderTest());
        assertThat(testSuiteResult().getMethodNames()).containsExactlyInAnyOrder(
            "testMethod",
            "paramTest",
            "paramTest",
            "paramTest",
            "paramTestWithNulls",
            "paramTestWithNulls"
        );

        assertThat(testCaseResult("testMethod")).isEqualTo(aPassedTestCaseResult());

        assertThat(firstTestCaseResult("paramTest")).isEqualTo(
            aPassedParamTestCaseResult()
                .withName("paramTest value 1")
                .withWordify("Passing assertion with value 1")
                //.withArgs(singletonList("value 1")) //TODO
                .build()
        );
        assertThat(secondTestCaseResult("paramTest")).isEqualTo(
            aPassedParamTestCaseResult()
                .withName("paramTest value 2")
                .withWordify("Passing assertion with value 2")
                //.withArgs(singletonList("value 2")) //TODO
                .build()
        );
        assertThat(thirdTestCaseResult("paramTest")).isEqualTo(
            aPassedParamTestCaseResult()
                .withName("paramTest value 3")
                .withWordify("Passing assertion with value 3")
                //.withArgs(singletonList("value 3")) //TODO
                .build()
        );
    }

    private TestCaseBuilder aPassedParamTestCaseResult() {
        return aTestCase()
            .withMethodName("paramTest")
            .withMethodNameWordified("Param test")
            .withStatus(PASSED)
            .withClassName(testSuiteClass())
            .withPackageName(testSuitePackage())
            .withNotes(null);
    }

    private TestCase aPassedTestCaseResult() {
        return aTestCase()
            .withName("testMethod")
            .withMethodNameWordified("Test method")
            .withMethodName("testMethod")
            .withWordify("Passing assertion")
            .withStatus(PASSED)
            .withClassName(testSuiteClass())
            .withPackageName(testSuitePackage())
            .withNotes(null)
            .build();
    }
}
