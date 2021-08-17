package io.bitsmart.bdd.report.report.model;

import java.util.List;
import java.util.Objects;

public class Report {
    // ReportIndex is for data. Maybe need one for html?
    private final DataReportIndex dataReportIndex;
    private final List<TestCase> testCases;
    private final List<TestSuite> testSuites;

    public Report(DataReportIndex dataReportIndex, List<TestCase> testCases, List<TestSuite> testSuites) {
        this.dataReportIndex = dataReportIndex;
        this.testCases = testCases;
        this.testSuites = testSuites;
    }
    
    public DataReportIndex getIndex() {
        return dataReportIndex;
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
            "reportIndex=" + dataReportIndex +
            ", testCases=" + testCases +
            ", testSuites=" + testSuites +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Report)) return false;
        Report report = (Report) o;
        return Objects.equals(dataReportIndex, report.dataReportIndex) && Objects.equals(testCases, report.testCases) && Objects.equals(testSuites, report.testSuites);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataReportIndex, testCases, testSuites);
    }
}
