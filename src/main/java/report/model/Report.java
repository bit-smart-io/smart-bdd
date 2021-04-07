package report.model;

import java.util.ArrayList;
import java.util.List;

public class Report {
    private List<TestCase> testCases = new ArrayList<>();
    private List<TestSuite> testSuites = new ArrayList<>();

    public List<TestCase> getTestCases() {
        return testCases;
    }

    public List<TestSuite> getTestSuites() {
        return testSuites;
    }

    public void addTestResult(TestCase testCase) {
        testCases.add(testCase);
    }

    public void addClassResult(TestSuite testSuite) {
       this.testSuites.add(testSuite);
    }
}
