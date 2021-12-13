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

import io.bitsmart.bdd.report.junit5.results.extension.ReportExtension;
import io.bitsmart.bdd.report.report.model.Report;
import io.bitsmart.bdd.report.report.model.TestSuiteNameToFile;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import shared.undertest.basic.FailedDueToExceptionTestCasesUnderTest;
import shared.undertest.basic.FailedTestCasesUnderTest;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class AbstractReportTest {
    static final Clock CLOCK = Clock.fixed(Instant.parse("2000-01-29T09:15:30.00Z"), ZoneId.of("UTC"));
    Report report;

    @BeforeAll
    public static void enableTest() {
        FailedTestCasesUnderTest.setEnabled(true);
        FailedDueToExceptionTestCasesUnderTest.setEnabled(true);
    }

    @AfterAll
    public static void disableTest() {
        FailedTestCasesUnderTest.setEnabled(false);
        FailedDueToExceptionTestCasesUnderTest.setEnabled(false);
    }

    @BeforeEach
    void setUp() {
        ReportExtension.getTestContext().reset();
    }

    void assertSuiteLinks(List<TestSuiteNameToFile> suiteNameToFiles) {
        assertThat(report.getIndex().getLinks().getTestSuites()).containsAll(suiteNameToFiles);
    }

//    @Test
//    void reportForOnePackageGeneratedCorrectly() {
//        TestLauncher.launch(selectPackage(PACKAGE_NAME));
//        Report report = ReportFactory.create(ReportExtension.getTestContext().getTestResults(), CLOCK);
//
//        TestSuiteLinks testSuiteLinks = report.getIndex().getLinks();
//        assertThat(testSuiteLinks.getTestSuites()).contains(
//            new TestSuiteNameToFile(
//                "shared.undertest.basic.ClassUnderTest",
//                "TEST-shared.undertest.basic.ClassUnderTest.json"));
//        assertThat(report.getIndex().getSummary()).isEqualTo(new TestSuiteSummary(28, 14, 4, 8, 4));
//        assertThat(report.getDateTime()).isEqualTo(ZonedDateTime.now(CLOCK));
//        assertPassingTestSuite(passingTestSuite(report));
//    }
//
//    private void assertSuiteLinks(TestSuiteLinks testSuiteLinks) {
//        List<TestSuiteNameToFile> suiteNameToFiles = Arrays.asList(
//            new TestSuiteNameToFile("shared.undertest.basic.ClassUnderTest", "TEST-shared.undertest.basic.ClassUnderTest.json"),
//            new TestSuiteNameToFile("shared.undertest.basic.OutputStreamClassUnderTest", "TEST-shared.undertest.basic.OutputStreamClassUnderTest.json"),
//            new TestSuiteNameToFile("shared.undertest.basic.FailedTestCasesUnderTest", "TEST-shared.undertest.basic.FailedTestCasesUnderTest.json"),
//            new TestSuiteNameToFile("shared.undertest.basic.FailedDueToExceptionTestCasesUnderTest", "TEST-shared.undertest.basic.FailedDueToExceptionTestCasesUnderTest.json"),
//            new TestSuiteNameToFile("shared.undertest.basic.DisabledTestCasesUnderTest", "TEST-shared.undertest.basic.DisabledTestCasesUnderTest.json"),
//            new TestSuiteNameToFile("shared.undertest.basic.TestNamesTest", "TEST-shared.undertest.basic.TestNamesTest.json"),
//            new TestSuiteNameToFile("shared.undertest.basic.AbortedTestCasesUnderTest", "TEST-shared.undertest.basic.AbortedTestCasesUnderTest.json"));
//        assertThat(testSuiteLinks.getTestSuites()).containsAll(suiteNameToFiles);
//    }
//
//    private TestSuite passingTestSuite(Report report) {
//        return Objects.requireNonNull(
//            report.getTestSuites().stream().findFirst().filter(suite -> suite.getClassName().equals("ClassUnderTest")).orElse(null)
//        );
//    }
}
