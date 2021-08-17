package io.bitsmart.bdd.report.report.model;

import java.util.List;
import java.util.Objects;

public class Report {
    private final ReportIndex reportIndex;
    private final List<TestCase> testCases;
    private final List<TestSuite> testSuites;

    public Report(ReportIndex reportIndex, List<TestCase> testCases, List<TestSuite> testSuites) {
        this.reportIndex = reportIndex;
        this.testCases = testCases;
        this.testSuites = testSuites;
    }
    
    public ReportIndex getIndex() {
        return reportIndex;
    }

    public List<TestCase> getTestCases() {
        return testCases;
    }

    public List<TestSuite> getTestSuites() {
        return testSuites;
    }

    @Override
    public String toString() {
        return "Report{" +
            "reportIndex=" + reportIndex +
            ", testCases=" + testCases +
            ", testSuites=" + testSuites +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Report)) return false;
        Report report = (Report) o;
        return Objects.equals(reportIndex, report.reportIndex) && Objects.equals(testCases, report.testCases) && Objects.equals(testSuites, report.testSuites);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reportIndex, testCases, testSuites);
    }
}
