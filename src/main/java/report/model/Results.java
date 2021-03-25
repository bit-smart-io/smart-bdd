package report.model;

import java.util.ArrayList;
import java.util.List;

public class Results {

    // map of packages to classes
    // package summaries

    private List<Result> results = new ArrayList<>();

    public List<Result> getResults() {
        return results;
    }

    public void addResult(Result result) {
        results.add(result);
    }

    // time from to
    // date
    // summary

    // classResults
      // testResults
        // testResult - for a single test
        // testResults - parameterised test
          // testResult
          // testResult

    // or are these containers?
    // root -> suits/packages -> classes -> tests
}
