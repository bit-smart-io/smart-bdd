package junit5.results;

import report.model.Result;
import report.model.Results;
import report.model.Status;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ReportFactory {

    public static Results create(AllResults allResults) {
        Results results = new Results();
        Collection<ClassResults> classResultsList = allResults.getClassNameToClassResults().values();
        List<TestResult> testResults = classResultsList.stream().flatMap(resultsForClass -> resultsForClass.getContextToTestResult().values().stream()).collect(Collectors.toList());

        testResults.forEach(resultsForTest -> {
            results.addResult(new Result(
                resultsForTest.getWordify(),
                statusFrom(resultsForTest.getStatus()),
                "method name",
                "class name",
                "package name")
            );
        });

        return results;
    }

    private static Status statusFrom(TestResult.Status status) {
        switch (status) {
            case PASSED:
                return Status.PASSED;
            default:
                return Status.FAILED;
        }
    }
}
