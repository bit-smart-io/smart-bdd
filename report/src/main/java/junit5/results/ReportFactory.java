package junit5.results;

import junit5.results.model.AllResults;
import junit5.results.model.TestCaseResult;
import junit5.results.model.TestCaseResultStatus;
import junit5.results.model.TestSuiteResults;
import junit5.results.model.TestSuiteResultsMetadata;
import report.model.HomePage;
import report.model.Report;
import report.model.Status;
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

    private static report.model.TestSuite testSuite(TestSuiteResults testSuiteResults) {
        return new report.model.TestSuite(
            testSuiteResults.getTestSuiteClass().getFullyQualifiedName(),
            testSuiteResults.getTestSuiteClass().getClassName(),
            testSuiteResults.getTestSuiteClass().getPackageName(),
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

    private static List<report.model.TestCase> testResults(List<TestCaseResult> testCaseResults) {
        return testCaseResults.stream().map(ReportFactory::testCase).collect(toList());
    }

    private static report.model.TestCase testCase(TestCaseResult testCaseResult) {
        return new report.model.TestCase(
            testCaseResult.getWordify(),
            statusFrom(testCaseResult.getStatus()),
            testCaseResult.getMethodName(),
            testCaseResult.getTestSuiteResultsId().getClassName(),
            testCaseResult.getTestSuiteResultsId().getPackageName());
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
