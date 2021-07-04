package io.bitsmart.bdd.ft.report.ports.json.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Note: this is a domain object holding view objects. This is okay for now.
 *
 *  TODO reportBuilder or are we okay updating this?
 */
public class Report {
    private ReportIndex reportIndex;
    private List<TestCase> testCases = new ArrayList<>();
    private List<TestSuite> testSuites = new ArrayList<>();

    public ReportIndex getHomePage() {
        return reportIndex;
    }

    public List<TestCase> getTestCases() {
        return testCases;
    }

    public List<TestSuite> getTestSuites() {
        return testSuites;
    }

    public void setReportIndex(ReportIndex reportIndex) {
        this.reportIndex = reportIndex;
    }

    public void addTestCase(TestCase testCase) {
        testCases.add(testCase);
    }

    public void addTestSuite(TestSuite testSuite) {
       this.testSuites.add(testSuite);
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
