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
    static final String DEFAULT_DATE_TIME = "2000-01-29T09:15:30.00Z";
    static final Clock CLOCK = Clock.fixed(Instant.parse(DEFAULT_DATE_TIME), ZoneId.of("UTC"));
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
}
