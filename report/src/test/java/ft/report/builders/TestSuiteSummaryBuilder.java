package ft.report.builders;

import io.bitsmart.bdd.report.report.model.TestSuiteSummary;
import io.bitsmart.bdd.report.utils.Builder;

public final class TestSuiteSummaryBuilder implements Builder<TestSuiteSummary> {
    private int testCaseCount;
    private int passedCount;
    private int skippedCount;
    private int failedCount;
    private int abortedCount;

    private TestSuiteSummaryBuilder() {
    }

    public static TestSuiteSummaryBuilder aTestSuiteSummary() {
        return new TestSuiteSummaryBuilder();
    }

    public TestSuiteSummaryBuilder withTestCaseCount(int testCaseCount) {
        this.testCaseCount = testCaseCount;
        return this;
    }

    public TestSuiteSummaryBuilder withPassedCount(int passedCount) {
        this.passedCount = passedCount;
        return this;
    }

    public TestSuiteSummaryBuilder withSkippedCount(int skippedCount) {
        this.skippedCount = skippedCount;
        return this;
    }

    public TestSuiteSummaryBuilder withFailedCount(int failedCount) {
        this.failedCount = failedCount;
        return this;
    }

    public TestSuiteSummaryBuilder withAbortedCount(int abortedCount) {
        this.abortedCount = abortedCount;
        return this;
    }

    public TestSuiteSummary build() {
        return new TestSuiteSummary(testCaseCount, passedCount, skippedCount, failedCount, abortedCount);
    }
}
