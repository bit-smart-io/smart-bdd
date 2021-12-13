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

package io.bitsmart.bdd.report.report.adapter;

import io.bitsmart.bdd.report.junit5.results.model.TestCaseResult;
import io.bitsmart.bdd.report.junit5.results.model.TestCaseResultStatus;
import io.bitsmart.bdd.report.junit5.results.model.TestMethod;
import io.bitsmart.bdd.report.junit5.results.model.TestResults;
import io.bitsmart.bdd.report.junit5.results.model.TestSuiteResult;
import io.bitsmart.bdd.report.junit5.results.model.TestSuiteResultsMetadata;
import io.bitsmart.bdd.report.junit5.results.model.notes.Notes;
import io.bitsmart.bdd.report.mermaid.SequenceDiagram;
import io.bitsmart.bdd.report.report.model.Report;
import io.bitsmart.bdd.report.report.model.DataReportIndex;
import io.bitsmart.bdd.report.report.model.Status;
import io.bitsmart.bdd.report.report.model.TestSuiteSummary;
import io.bitsmart.bdd.report.report.writers.DataFileNameProvider;

import java.time.Clock;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class ReportFactory {
    private static final ReportTestSuiteLinksFactory dataTestSuiteLinksFactory = new ReportTestSuiteLinksFactory(new DataFileNameProvider());

    public static Report create(TestResults testResults, Clock clock) {
        Collection<TestSuiteResult> testSuiteResults = testResults.getTestSuiteResults();
        List<io.bitsmart.bdd.report.report.model.TestSuite> testSuites = testSuites(testSuiteResults);
        List<io.bitsmart.bdd.report.report.model.TestCase> testCases = testCases(testSuiteResults);

         DataReportIndex dataReportIndex = new DataReportIndex(
             dataTestSuiteLinksFactory.create(testSuites),
            ReportSummaryFactory.create(testSuites));
        return new Report(dataReportIndex, testCases, testSuites, ZonedDateTime.now(clock));
    }

    private static List<io.bitsmart.bdd.report.report.model.TestCase> testCases(Collection<TestSuiteResult> testSuiteResults) {
        return testSuiteResults.stream()
            .flatMap(testSuite -> testSuite.getTestCaseResults().stream())
            .map(ReportFactory::testCase)
            .collect(toList());
    }

    private static List<io.bitsmart.bdd.report.report.model.TestSuite> testSuites(Collection<TestSuiteResult> testSuiteResults) {
        return testSuiteResults.stream().map(ReportFactory::testSuite).collect(toList());
    }

    public static io.bitsmart.bdd.report.report.model.TestSuite testSuite(TestSuiteResult testSuiteResult) {
        return new io.bitsmart.bdd.report.report.model.TestSuite(
            testSuiteResult.getTitle(),
            testSuiteResult.getTestSuiteClass().getFullyQualifiedName(),
            testSuiteResult.getTestSuiteClass().getClassName(),
            testSuiteResult.getTestSuiteClass().getPackageName(),
            testSuiteResult.getMethods().stream().map(TestMethod::getName).collect(toList()),
            testResults(testSuiteResult.getTestCaseResults()),
            testSuiteSummary(testSuiteResult.getMetadata()),
            notes(testSuiteResult.getNotes()));
    }

    private static TestSuiteSummary testSuiteSummary(TestSuiteResultsMetadata metadata) {
        return new TestSuiteSummary(
            metadata.getTestCaseCount(),
            metadata.getPassedCount(),
            metadata.getAbortedCount(),
            metadata.getFailedCount(),
            metadata.getAbortedCount());
    }

    private static List<io.bitsmart.bdd.report.report.model.TestCase> testResults(List<TestCaseResult> testCaseResults) {
        return testCaseResults.stream().map(ReportFactory::testCase).collect(toList());
    }

    private static io.bitsmart.bdd.report.report.model.TestCase testCase(TestCaseResult testCaseResult) {
        return new io.bitsmart.bdd.report.report.model.TestCase(
            testCaseResult.getWordify(),
            statusFrom(testCaseResult.getStatus()),
            testCaseResult.getName(),
            testCaseResult.getMethod().getName(),
            testCaseResult.getMethod().getWordify(),
            testCaseResult.getTestSuiteClass().getClassName(),
            testCaseResult.getTestSuiteClass().getPackageName(),
            notes(testCaseResult.getNotes()));
    }

    private static io.bitsmart.bdd.report.report.model.notes.Notes notes(Notes notes) {
        if (notes.text().getNotes().size() == 0 && notes.diagrams().getAll().size() ==  0) {
            return null;
        }

        List<SequenceDiagram> diagrams = new ArrayList<>(notes.diagrams().getAll().values());
        List<String> generatedDiagrams = diagrams.stream().map(SequenceDiagram::generate).collect(toList());
        return new io.bitsmart.bdd.report.report.model.notes.Notes(notes.text().getNotes(), generatedDiagrams);
    }

    private static Status statusFrom(TestCaseResultStatus status) {
        switch (status) {
            case PASSED:
                return Status.PASSED;
            default:
                return Status.FAILED;
        }
    }
}
