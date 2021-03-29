package report.model;

import java.util.ArrayList;
import java.util.List;

public class Report {
    private List<TestResult> testResults = new ArrayList<>();
    private List<ClassResults> classResultsList = new ArrayList<>();

    public List<TestResult> getTestResults() {
        return testResults;
    }

    public List<ClassResults> getClassResultsList() {
        return classResultsList;
    }

    public void addTestResult(TestResult testResult) {
        testResults.add(testResult);
    }

    public void addClassResult(ClassResults classResults) {
       this.classResultsList.add(classResults);
    }
}
