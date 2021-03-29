package junit5.results;

import report.model.ClassResults;
import report.model.Report;
import report.model.Status;
import report.model.TestResult;

import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class ReportFactory {

    public static Report create(AllResults allResults) {
        Report report = new Report();
        Collection<junit5.results.ClassResults> classResultsList = allResults.getClassNameToClassResults().values();

        classResultsList.forEach(classResult -> report.addClassResult(classResults(classResult)));

        List<junit5.results.TestResult> allTestResults = classResultsList.stream()
            .flatMap(classResults -> classResults.getContextToTestResult().values().stream())
            .collect(toList());

        allTestResults.forEach(testResult ->
            report.addTestResult(testResult(testResult))
        );

        return report;
    }

    private static ClassResults classResults(junit5.results.ClassResults classResults) {
        return new ClassResults(
            classResults.getClassName(),
            classResults.getPackageName(),
            classResults.getMethodNames(),
            testResults(classResults.getTestResults()));
    }

    private static List<TestResult> testResults(List<junit5.results.TestResult> testResults) {
        return testResults.stream().map(ReportFactory::testResult).collect(toList());
    }

    private static TestResult testResult(junit5.results.TestResult testResult) {
        return new TestResult(
            testResult.getWordify(),
            statusFrom(testResult.getStatus()),
            testResult.getMethodName(),
            testResult.getClassName(),
            testResult.getPackageName());
    }

    private static Status statusFrom(junit5.results.TestResult.Status status) {
        switch (status) {
            case PASSED:
                return Status.PASSED;
            default:
                return Status.FAILED;
        }
    }
}
