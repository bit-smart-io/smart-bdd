package junit5.results;

import junit5.results.model.AllResults;
import junit5.results.model.TestCaseResult;
import junit5.results.model.TestSuiteResults;
import report.model.TestSuite;
import report.model.Report;
import report.model.Status;
import report.model.TestCase;

import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class ReportFactory {

    public static Report create(AllResults allResults) {
        Report report = new Report();
        Collection<TestSuiteResults> testSuiteResultsList = allResults.getClassNameToClassResults().values();

        testSuiteResultsList.forEach(classResult -> report.addClassResult(classResults(classResult)));

        List<TestCaseResult> allTestCaseResults = testSuiteResultsList.stream()
            .flatMap(classResults -> classResults.getContextToTestResult().values().stream())
            .collect(toList());

        allTestCaseResults.forEach(testResult ->
            report.addTestResult(testResult(testResult))
        );

        return report;
    }

    private static TestSuite classResults(TestSuiteResults testSuiteResults) {
        return new TestSuite(
            testSuiteResults.getName(),
            testSuiteResults.getClassName(),
            testSuiteResults.getPackageName(),
            testSuiteResults.getMethodNames(),
            testResults(testSuiteResults.getTestResults()));
    }

    private static List<TestCase> testResults(List<TestCaseResult> testCaseResults) {
        return testCaseResults.stream().map(ReportFactory::testResult).collect(toList());
    }

    private static TestCase testResult(TestCaseResult testCaseResult) {
        return new TestCase(
            testCaseResult.getWordify(),
            statusFrom(testCaseResult.getStatus()),
            testCaseResult.getMethodName(),
            testCaseResult.getClassName(),
            testCaseResult.getPackageName());
    }

    private static Status statusFrom(TestCaseResult.Status status) {
        switch (status) {
            case PASSED:
                return Status.PASSED;
            default:
                return Status.FAILED;
        }
    }
}
