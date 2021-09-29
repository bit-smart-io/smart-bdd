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
import io.bitsmart.bdd.report.junit5.results.extension.ReportExtension;
import io.bitsmart.bdd.report.report.adapter.ReportFactory;
import io.bitsmart.bdd.report.report.model.Report;
import io.bitsmart.bdd.report.report.model.TestSuiteLinks;
import io.bitsmart.bdd.report.report.model.TestSuiteNameToFile;
import io.bitsmart.bdd.report.report.model.TestSuiteSummary;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.undertest.ClassUnderTest;
import shared.undertest.FailedDueToExceptionTestCasesUnderTest;
import shared.undertest.FailedTestCasesUnderTest;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectPackage;

public class ReportForPackageTest {
    private static final String PACKAGE_NAME = ClassUnderTest.class.getPackage().getName();

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

    @Test
    void reportForOnePackageGeneratedCorrectly() throws IOException {
        TestLauncher.launch(selectPackage(PACKAGE_NAME));

        Report report = ReportFactory.create(ReportExtension.getTestContext().getTestResults());
        TestSuiteLinks testSuiteLinks = report.getIndex().getLinks();
        assertThat(testSuiteLinks.getTestSuites()).contains(
            new TestSuiteNameToFile(
                "shared.undertest.ClassUnderTest",
                "TEST-shared.undertest.ClassUnderTest.json"));
        assertThat(report.getIndex().getSummary()).isEqualTo(new TestSuiteSummary(26, 12, 4, 8, 4));
    }
}
