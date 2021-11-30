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

package component.report;

import io.bitsmart.bdd.report.report.model.Status;
import io.bitsmart.bdd.report.report.model.TestCase;
import io.bitsmart.bdd.report.report.model.TestSuite;

import static component.report.builders.TestCaseBuilder.aTestCase;
import static component.report.builders.TestSuiteSummaryBuilder.aTestSuiteSummary;
import static org.assertj.core.api.Assertions.assertThat;

public class ReportAssertions {

    public static void assertPassingTestSuite(TestSuite testSuite) {
        assertThat(testSuite.getName()).isEqualTo("shared.undertest.basic.ClassUnderTest");
        assertThat(testSuite.getClassName()).isEqualTo("ClassUnderTest");
        assertThat(testSuite.getPackageName()).isEqualTo("shared.undertest.basic");
        assertThat(testSuite.getMethodNames()).containsExactly(
            "testMethod", "paramTest", "paramTest", "paramTest", "paramTestWithNulls", "paramTestWithNulls");
        assertThat(testSuite.getSummary()).isEqualTo(
            aTestSuiteSummary()
                .withTestCase(6)
                .withPassed(6)
                .build());
        assertThat(testSuite.getTestCases()).contains(passingTestCase());
        assertThat(testSuite.getTestCases()).contains(passingParamTestCase());
    }

    public static TestCase passingTestCase() {
        return aTestCase()
            .withWordify("Passing assertion")
            .withStatus(Status.PASSED)
            .withName("testMethod")
            .withMethodName("testMethod")
            .withMethodNamWordified("Test method")
            .withClassName("ClassUnderTest")
            .withPackageName("shared.undertest.basic")
            .build();
    }

    public static TestCase passingParamTestCase() {
        return aTestCase()
            .withWordify("Passing assertion with value 1")
            .withStatus(Status.PASSED)
            .withName("paramTest value 1")
            .withMethodName("paramTest")
            .withMethodNamWordified("Param test")
            .withClassName("ClassUnderTest")
            .withPackageName("shared.undertest.basic")
            .build();
    }
}
