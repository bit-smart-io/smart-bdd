package junit5.results;

import junit5.results.model.Results;
import junit5.results.model.TestCaseResult;
import junit5.results.model.TestCaseResultStatus;
import junit5.results.model.TestSuiteResult;
import junit5.results.model.TestSuiteResultsMetadata;
import report.model.HomePage;
import report.model.Report;
import report.model.Status;
import report.model.TestSuiteSummary;

import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class ReportFactory {

    public static Report create(Results results) {
        Report report = new Report();
        Collection<TestSuiteResult> testSuiteResults = results.getTestSuiteResults();
        testSuiteResults.forEach(testSuiteResult -> report.addTestSuite(testSuite(testSuiteResult)));
        testSuiteResults.stream()
            .flatMap(testSuite -> testSuite.getTestCaseResults().stream())
            .collect(toList())
            .forEach(testCaseResult -> report.addTestCase(testCase(testCaseResult)));

        report.setHomePage(
            new HomePage(ReportIndexFactory.create(report),
                ReportSummaryFactory.create(report.getTestSuites())));
        return report;
    }

    private static report.model.TestSuite testSuite(TestSuiteResult testSuiteResult) {
        return new report.model.TestSuite(
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

    private static List<report.model.TestCase> testResults(List<TestCaseResult> testCaseResults) {
        return testCaseResults.stream().map(ReportFactory::testCase).collect(toList());
    }

    private static report.model.TestCase testCase(TestCaseResult testCaseResult) {
        return new report.model.TestCase(
            testCaseResult.getWordify(),
            statusFrom(testCaseResult.getStatus()),
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
