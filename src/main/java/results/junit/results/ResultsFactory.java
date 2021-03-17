package results.junit.results;

import results.domain.Result;
import results.domain.Results;
import results.domain.Status;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ResultsFactory {

    public Results create(ResultsForClasses resultsForClasses) {
        Results results = new Results();
        Collection<ResultsForClass> resultsForClassList = resultsForClasses.getResultsForClasses().values();
        List<ResultsForTest> resultsForTests = resultsForClassList.stream().flatMap(resultsForClass -> resultsForClass.getContextToResultsForTest().values().stream()).collect(Collectors.toList());

        resultsForTests.forEach(resultsForTest -> {
            results.addResult(new Result(resultsForTest.getWordify(), statusFrom(resultsForTest.getStatus())));
        });

        return results;
    }

    private Status statusFrom(ResultsForTest.Status status) {
        switch (status) {
            case PASSED:
                return Status.PASSED;
            default:
                return Status.FAILED;
        }
    }
}
