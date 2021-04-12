package junit5.results.model;

public final class TestSuiteResultsMetadataBuilder {
    private int testCaseCount = 0;
    private int passedCount = 0;
    private int skippedCount = 0;
    private int failedCount = 0;
    private int abortedCount = 0;

    private TestSuiteResultsMetadataBuilder() {
    }

    public static TestSuiteResultsMetadataBuilder aTestSuiteResultsMetadata() {
        return new TestSuiteResultsMetadataBuilder();
    }

    public TestSuiteResultsMetadataBuilder withTestCaseCount(int testCaseCount) {
        this.testCaseCount = testCaseCount;
        return this;
    }

    public TestSuiteResultsMetadataBuilder withPassedCount(int passedCount) {
        this.passedCount = passedCount;
        return this;
    }

    public TestSuiteResultsMetadataBuilder withSkippedCount(int skippedCount) {
        this.skippedCount = skippedCount;
        return this;
    }

    public TestSuiteResultsMetadataBuilder withFailedCount(int failedCount) {
        this.failedCount = failedCount;
        return this;
    }

    public TestSuiteResultsMetadataBuilder withAbortedCount(int abortedCount) {
        this.abortedCount = abortedCount;
        return this;
    }

    public TestSuiteResultsMetadata build() {
        return new TestSuiteResultsMetadata(testCaseCount, passedCount, skippedCount, failedCount, abortedCount);
    }
}
