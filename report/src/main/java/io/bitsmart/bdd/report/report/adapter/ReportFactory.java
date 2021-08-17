package io.bitsmart.bdd.report.report.adapter;

import io.bitsmart.bdd.report.junit5.results.model.TestResults;
import io.bitsmart.bdd.report.junit5.results.model.TestCaseResult;
import io.bitsmart.bdd.report.junit5.results.model.TestCaseResultStatus;
import io.bitsmart.bdd.report.junit5.results.model.TestSuiteResult;
import io.bitsmart.bdd.report.junit5.results.model.TestSuiteResultsMetadata;
import io.bitsmart.bdd.report.report.model.ReportIndex;
import io.bitsmart.bdd.report.report.model.Report;
import io.bitsmart.bdd.report.report.model.Status;
import io.bitsmart.bdd.report.report.model.TestSuiteSummary;

import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class ReportFactory {

    public static Report create(TestResults testResults) {
        Collection<TestSuiteResult> testSuiteResults = testResults.getTestSuiteResults();
        List<io.bitsmart.bdd.report.report.model.TestSuite> testSuites = testSuites(testSuiteResults);
        List<io.bitsmart.bdd.report.report.model.TestCase> testCases = testCases(testSuiteResults);
        ReportIndex reportIndex = new ReportIndex(
            ReportTestSuiteLinksFactory.create(testSuites),
            ReportSummaryFactory.create(testSuites));

        return new Report(reportIndex, testCases, testSuites);
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

    private static io.bitsmart.bdd.report.report.model.TestSuite testSuite(TestSuiteResult testSuiteResult) {
        return new io.bitsmart.bdd.report.report.model.TestSuite(
            testSuiteResult.getTestSuiteClass().getFullyQualifiedName(),
            testSuiteResult.getTestSuiteClass().getClassName(),
            testSuiteResult.getTestSuiteClass().getPackageName(),
            testSuiteResult.getMethodNames(),
            testResults(testSuiteResult.getTestCaseResults()),
            testSuiteSummary(testSuiteResult.getMetadata()));
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
            testCaseResult.getMethodName(),
            testCaseResult.getTestSuiteClass().getClassName(),
            testCaseResult.getTestSuiteClass().getPackageName());
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
