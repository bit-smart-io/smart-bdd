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

package io.bitsmart.bdd.ft.html;

import io.bitsmart.bdd.ft.report.infrastructure.utils.HtmlReportTestUtils;
import io.bitsmart.bdd.ft.report.launcher.TestExecutionListener;
import io.bitsmart.bdd.ft.report.launcher.TestLauncher;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.time.LocalDateTime;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

public abstract class AbstractResultsForHtml {
    private String testSuite;
    private String reportIndex;
    private LocalDateTime startTime;

    @BeforeEach
    void beforeEach() throws IOException {
        startTime = LocalDateTime.now();
        TestExecutionListener testListener = new TestExecutionListener();
        TestLauncher.launch(classUnderTest(), testListener);
        await().atMost(5, SECONDS).until(testListener::testsHasFinished);
        testSuite = HtmlReportTestUtils.loadTestSuite(classUnderTest());
        reportIndex = HtmlReportTestUtils.loadReportIndex();
    }

    public abstract Class<?> classUnderTest();

    public String reportIndex() {
        return reportIndex;
    }

    public String testSuite() {
        return testSuite;
    }
}
