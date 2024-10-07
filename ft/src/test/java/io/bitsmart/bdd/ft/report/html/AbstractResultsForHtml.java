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

package io.bitsmart.bdd.ft.report.html;

import io.bitsmart.bdd.ft.common.AbstractReportTest;
import io.bitsmart.bdd.ft.report.infrastructure.utils.TestConfig;
import io.bitsmart.bdd.ft.report.launcher.TestExecutionListener;
import io.bitsmart.bdd.ft.report.launcher.TestLauncher;
import io.bitsmart.bdd.report.config.ResolvedSmartBddConfig;
import io.bitsmart.bdd.report.config.SmartBddConfig;
import io.bitsmart.bdd.report.junit5.results.extension.SmartReport;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalDateTime;

import static io.bitsmart.bdd.ft.report.infrastructure.utils.HtmlReportTestUtils.loadReportIndex;
import static io.bitsmart.bdd.ft.report.infrastructure.utils.HtmlReportTestUtils.loadTestSuite;
import static io.bitsmart.bdd.ft.report.infrastructure.utils.TestConfig.inMemoryDirectory;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

public abstract class AbstractResultsForHtml extends AbstractReportTest {
    private static final Logger log = LoggerFactory.getLogger(AbstractResultsForHtml.class.getName());
    private String testSuite;
    private String reportIndex;
    private LocalDateTime startTime;

    @BeforeAll
    static void setPath() {
        // do gradlew dir=in-mem, root, class, tmp-dir
        // run this as failsafe/it
        if (inMemoryDirectory) {
            SmartBddConfig.overrideBasePath(TestConfig.getBasePath());
            log.info("basePath: " + TestConfig.getBasePath());
            log.info("resolved path: " + ResolvedSmartBddConfig.getBasePath().resolve(SmartBddConfig.getDataFolder()));
        }
    }

    @BeforeEach
    void beforeEach() throws IOException {
        SmartReport.getTestContext().reset();
        startTime = LocalDateTime.now();
        TestExecutionListener testListener = new TestExecutionListener();
        TestLauncher.launch(classUnderTest(), testListener);
        await().atMost(5, SECONDS).until(testListener::testsHasFinished);
        testSuite = loadTestSuite(classUnderTest());
        reportIndex = loadReportIndex();
    }

    public abstract Class<?> classUnderTest();

    public String reportIndex() {
        return reportIndex;
    }

    public String testSuite() {
        return testSuite;
    }
}
