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

import io.bitsmart.bdd.ft.report.infrastructure.json.builders.TestSuiteNameToFileBuilder;
import io.bitsmart.bdd.ft.report.infrastructure.json.builders.TestSuiteSummaryBuilder;
import io.bitsmart.bdd.ft.report.infrastructure.json.model.DataReportIndex;
import io.bitsmart.bdd.ft.report.infrastructure.json.model.TestCase;
import io.bitsmart.bdd.ft.report.infrastructure.json.model.TestSuite;
import io.bitsmart.bdd.ft.report.launcher.TestExecutionListener;
import io.bitsmart.bdd.ft.report.launcher.TestLauncher;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static io.bitsmart.bdd.ft.report.infrastructure.json.builders.TestSuiteLinksBuilder.aTestSuiteLinks;
import static io.bitsmart.bdd.ft.report.infrastructure.utils.DataReportTestUtils.loadReportIndex;
import static io.bitsmart.bdd.ft.report.infrastructure.utils.DataReportTestUtils.loadTestSuite;
import static java.util.Collections.singletonList;
import static java.util.concurrent.TimeUnit.SECONDS;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

public abstract class AbstractResultsForData {
    private TestSuite testSuite;
    private DataReportIndex reportIndex;
    private LocalDateTime startTime;

    @BeforeEach
    void beforeEach() throws IOException {
        startTime = LocalDateTime.now();
        TestExecutionListener testListener = new TestExecutionListener();
        TestLauncher.launch(classUnderTest(), testListener);
        await().atMost(5, SECONDS).until(testListener::testsHasFinished);
        testSuite = loadTestSuite(classUnderTest());
        reportIndex = loadReportIndex();
    }

    public abstract Class<?> classUnderTest();

    public String testSuiteClass() {
        return classUnderTest().getSimpleName();
    }

    public String testSuitePackage() {
        return classUnderTest().getPackage().getName();
    }

    public TestCase testCaseResult(int index) {
        return testSuite.getTestCases().get(index);
    }

    public TestCase testCaseResult(String methodName) {
        return testSuiteResult().getTestCases().stream()
            .findAny()
            .filter(testCase -> testCase.getMethodName().equals(methodName))
            .orElse(null);
    }

    public List<TestCase> testCaseResults(String methodName) {
        return testSuiteResult().getTestCases().stream()
            .filter(testCase -> testCase.getMethodName().equals(methodName))
            .collect(toList());
    }

    public TestCase firstTestCaseResult(String methodName) {
        return testCaseResults(methodName).get(0);
    }

    public TestCase secondTestCaseResult(String methodName) {
        return testCaseResults(methodName).get(1);
    }

    public TestCase thirdTestCaseResult(String methodName) {
        return testCaseResults(methodName).get(2);
    }

    public TestSuite testSuiteResult() {
        return testSuite;
    }

    protected void assertTestSuitClass(TestSuite testSuite, Class<?> clazz) {
        assertThat(testSuite.getClassName()).isEqualTo(clazz.getSimpleName());
    }

    //TODO implement
//    protected void assertCauseWithMessage(TestCase result, String message) {
//        Throwable cause = result.getCause().orElseThrow(() -> new RuntimeException("Expected cause"));
//        assertThat(cause.getMessage()).isEqualTo(message);
//        assertThat(cause.getClass()).isNotNull();
//        assertThat(cause.getCause()).isNull();
//        assertThat(cause.getStackTrace()).isNotNull();
//    }
//
//    protected void assertEqualsIgnoringCause(TestCase actual, TestCase expected) {
//        assertThat(actual).isEqualToIgnoringGivenFields(expected, "cause");
//    }

    public void assertIndexLinks(TestSuiteNameToFileBuilder testSuiteNameToFile) {
        assertThat(reportIndex.getLinks()).isEqualTo(aTestSuiteLinks().withTestSuites(singletonList(testSuiteNameToFile)).build());
    }

    public void assertIndexSummary(TestSuiteSummaryBuilder testSuiteSummary) {
        assertThat(reportIndex.getSummary()).isEqualTo(testSuiteSummary.build());
    }

    public void assertIndexTimeStamp() {
        assertThat(reportIndex.getTimeStamp()).isNotNull();
        String timeStamp = reportIndex.getTimeStamp();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SS'Z'").withZone(ZoneId.systemDefault());
        LocalDateTime dateTime = LocalDateTime.from(formatter.parse(timeStamp));
        assertThat(dateTime).isAfter(startTime);
    }
}
