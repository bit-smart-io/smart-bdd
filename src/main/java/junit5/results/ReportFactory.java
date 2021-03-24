package junit5.results;

import report.Result;
import report.Results;
import report.Status;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ReportFactory {

    public static Results create(ResultsForClasses resultsForClasses) {
        Results results = new Results();
        Collection<ResultsForClass> resultsForClassList = resultsForClasses.getResultsForClasses().values();
        List<ResultsForTest> resultsForTests = resultsForClassList.stream().flatMap(resultsForClass -> resultsForClass.getContextToResultsForTest().values().stream()).collect(Collectors.toList());

        resultsForTests.forEach(resultsForTest -> {
            results.addResult(new Result(
                resultsForTest.getWordify(),
                statusFrom(resultsForTest.getStatus()),
                "methodName",
                "className",
                "packageName")
            );
        });

        return results;
    }

    private static Status statusFrom(ResultsForTest.Status status) {
        switch (status) {
            case PASSED:
                return Status.PASSED;
            default:
                return Status.FAILED;
        }
    }
}
