package junit5.results;

import junit5.results.model.AllResults;
import junit5.results.model.TestCaseResult;
import junit5.results.model.TestCaseStatus;
import junit5.results.model.TestSuiteResults;
import junit5.results.model.TestSuiteResultsMetadata;
import report.model.HomePage;
import report.model.TestSuite;
import report.model.Report;
import report.model.Status;
import report.model.TestCase;
import report.model.TestSuiteSummary;

import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class ReportFactory {

    public static Report create(AllResults allResults) {
        Report report = new Report();
        Collection<TestSuiteResults> testSuiteResultsList = allResults.getClassNameToTestSuiteResults().values();
        testSuiteResultsList.forEach(testSuiteResults -> report.addTestSuite(testSuite(testSuiteResults)));
        testSuiteResultsList.stream()
            .flatMap(testSuite -> testSuite.getContextToTestResult().values().stream())
            .collect(toList())
            .forEach(testCase -> report.addTestCase(testCase(testCase)));

        report.setHomePage(
            new HomePage(ReportIndexFactory.create(report),
                ReportSummaryFactory.create(report.getTestSuites())));
        return report;
    }

    private static TestSuite testSuite(TestSuiteResults testSuiteResults) {
        return new TestSuite(
            testSuiteResults.getResultsId().getName(),
            testSuiteResults.getResultsId().getClassName(),
            testSuiteResults.getResultsId().getPackageName(),
            testSuiteResults.getMethodNames(),
            testResults(testSuiteResults.getTestResults()),
            testSuiteSummary(testSuiteResults.getResultsMetadata()));
    }

    private static TestSuiteSummary testSuiteSummary(TestSuiteResultsMetadata metadata) {
        return new TestSuiteSummary(
            metadata.getTestCaseCount(),
            metadata.getPassedCount(),
            metadata.getAbortedCount(),
            metadata.getFailedCount(),
            metadata.getAbortedCount());
    }

    private static List<TestCase> testResults(List<TestCaseResult> testCaseResults) {
        return testCaseResults.stream().map(ReportFactory::testCase).collect(toList());
    }

    private static TestCase testCase(TestCaseResult testCaseResult) {
        return new TestCase(
            testCaseResult.getWordify(),
            statusFrom(testCaseResult.getStatus()),
            testCaseResult.getMethodName(),
            testCaseResult.getTestSuiteResultsId().getClassName(),
            testCaseResult.getTestSuiteResultsId().getPackageName());
    }

    private static Status statusFrom(TestCaseStatus status) {
        switch (status) {
            case PASSED:
                return Status.PASSED;
            default:
                return Status.FAILED;
        }
    }
}
