package io.bitsmart.bdd.report.junit5.results.model;

import java.util.Objects;

public class TestSuiteResultsMetadata {
    //TODO count class?
    private final int testCaseCount;
    private final int passedCount;
    private final int skippedCount;
    private final int failedCount;
    private final int abortedCount;
//    private Datetime timestamp="2021-03-30T20:03:44"
//    private String hostname="Jamess-MacBook-Pro.local"
//    privat long time="0.021"

    public TestSuiteResultsMetadata(int testCaseCount, int passedCount, int skippedCount, int failedCount, int abortedCount) {
        this.testCaseCount = testCaseCount;
        this.passedCount = passedCount;
        this.skippedCount = skippedCount;
        this.failedCount = failedCount;
        this.abortedCount = abortedCount;
    }

    public int getTestCaseCount() {
        return testCaseCount;
    }

    public int getPassedCount() {
        return passedCount;
    }

    public int getSkippedCount() {
        return skippedCount;
    }

    public int getFailedCount() {
        return failedCount;
    }

    public int getAbortedCount() {
        return abortedCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TestSuiteResultsMetadata)) return false;
        TestSuiteResultsMetadata that = (TestSuiteResultsMetadata) o;
        return testCaseCount == that.testCaseCount && passedCount == that.passedCount && skippedCount == that.skippedCount && failedCount == that.failedCount && abortedCount == that.abortedCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(testCaseCount, passedCount, skippedCount, failedCount, abortedCount);
    }

    @Override
    public String toString() {
        return "TestSuiteResultsMetadata{" +
            "testCaseCount=" + testCaseCount +
            ", passedCount=" + passedCount +
            ", skippedCount=" + skippedCount +
            ", failedCount=" + failedCount +
            ", abortedCount=" + abortedCount +
            '}';
    }
}
