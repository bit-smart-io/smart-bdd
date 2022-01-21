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

import io.bitsmart.bdd.report.junit5.launcher.TestLauncher;
import io.bitsmart.bdd.report.junit5.results.extension.SmartReport;
import io.bitsmart.bdd.report.report.adapter.ReportFactory;
import io.bitsmart.bdd.report.report.model.Report;
import io.bitsmart.bdd.report.report.model.TestSuite;
import io.bitsmart.bdd.report.report.model.TestSuiteNameToFile;
import io.bitsmart.bdd.report.report.model.TestSuiteSummary;
import org.junit.jupiter.api.Test;
import shared.undertest.basic.ClassUnderTest;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static component.report.ReportAssertions.assertPassingTestSuite;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectPackage;

public class ReportForPackageTest extends AbstractReportTest {
    private static final String PACKAGE_NAME = ClassUnderTest.class.getPackage().getName();

    @Test
    void reportForOnePackageGeneratedCorrectly() {
        TestLauncher.launch(selectPackage(PACKAGE_NAME));
        report = ReportFactory.create(SmartReport.getTestContext().getTestResults(), CLOCK);

        assertSuiteLinks();
        assertThat(report.getIndex().getSummary()).isEqualTo(new TestSuiteSummary(28, 14, 4, 8, 4));
        assertThat(report.getTimeStamp()).isEqualTo(DEFAULT_DATE_TIME);
        assertThat(report.getTestCases()).hasSize(28);
        assertThat(report.getTestSuites()).hasSize(7);
        assertPassingTestSuite(passingTestSuite(report));
    }

    private void assertSuiteLinks() {
        List<TestSuiteNameToFile> suiteNameToFiles = Arrays.asList(
            new TestSuiteNameToFile("shared.undertest.basic.ClassUnderTest", "TEST-shared.undertest.basic.ClassUnderTest.json"),
            new TestSuiteNameToFile("shared.undertest.basic.OutputStreamClassUnderTest", "TEST-shared.undertest.basic.OutputStreamClassUnderTest.json"),
            new TestSuiteNameToFile("shared.undertest.basic.FailedTestCasesUnderTest", "TEST-shared.undertest.basic.FailedTestCasesUnderTest.json"),
            new TestSuiteNameToFile("shared.undertest.basic.FailedDueToExceptionTestCasesUnderTest", "TEST-shared.undertest.basic.FailedDueToExceptionTestCasesUnderTest.json"),
            new TestSuiteNameToFile("shared.undertest.basic.DisabledTestCasesUnderTest", "TEST-shared.undertest.basic.DisabledTestCasesUnderTest.json"),
            new TestSuiteNameToFile("shared.undertest.basic.TestNamesTest", "TEST-shared.undertest.basic.TestNamesTest.json"),
            new TestSuiteNameToFile("shared.undertest.basic.AbortedTestCasesUnderTest", "TEST-shared.undertest.basic.AbortedTestCasesUnderTest.json"));
        assertSuiteLinks(suiteNameToFiles);
    }

    private TestSuite passingTestSuite(Report report) {
        return Objects.requireNonNull(
            report.getTestSuites().stream().findFirst().filter(suite -> suite.getClassName().equals("ClassUnderTest")).orElse(null)
        );
    }
}
