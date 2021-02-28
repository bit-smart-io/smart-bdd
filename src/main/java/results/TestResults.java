package results;

import java.util.ArrayList;
import java.util.List;

public class TestResults {
    List<TestResult> results = new ArrayList<>();

    public List<TestResult> getResults() {
        return results;
    }

    public void addResult(TestResult result) {
        this.results.add(result);
    }
}
