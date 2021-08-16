package component.report.builders;

import io.bitsmart.bdd.report.report.model.TestSuiteSummary;
import io.bitsmart.bdd.report.utils.Builder;

public final class TestSuiteSummaryBuilder implements Builder<TestSuiteSummary> {
    private int testCase;
    private int passed;
    private int skipped;
    private int failed;
    private int aborted;

    private TestSuiteSummaryBuilder() {
    }

    public static TestSuiteSummaryBuilder aTestSuiteSummary() {
        return new TestSuiteSummaryBuilder();
    }

    public TestSuiteSummaryBuilder withTestCase(int testCaseCount) {
        this.testCase = testCaseCount;
        return this;
    }

    public TestSuiteSummaryBuilder withPassed(int passedCount) {
        this.passed = passedCount;
        return this;
    }

    public TestSuiteSummaryBuilder withSkipped(int skippedCount) {
        this.skipped = skippedCount;
        return this;
    }

    public TestSuiteSummaryBuilder withFailed(int failedCount) {
        this.failed = failedCount;
        return this;
    }

    public TestSuiteSummaryBuilder withAborted(int abortedCount) {
        this.aborted = abortedCount;
        return this;
    }

    public TestSuiteSummary build() {
        return new TestSuiteSummary(testCase, passed, skipped, failed, aborted);
    }
}
