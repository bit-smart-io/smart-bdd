package junit5.results.model;

//TODO move to test
public final class TestCaseResultBuilder {
    private String wordify;
    private TestCaseStatus status;
    private String methodName;
    private TestSuiteResultsId testSuiteResultsId;

    private TestCaseResultBuilder() {
    }

    public static TestCaseResultBuilder aTestCaseResult() {
        return new TestCaseResultBuilder();
    }

    public TestCaseResultBuilder withWordify(String wordify) {
        this.wordify = wordify;
        return this;
    }

    public TestCaseResultBuilder withStatus(TestCaseStatus status) {
        this.status = status;
        return this;
    }

    public TestCaseResultBuilder withMethodName(String methodName) {
        this.methodName = methodName;
        return this;
    }

    public TestCaseResultBuilder withTestSuiteResultsId(TestSuiteResultsId testSuiteResultsId) {
        this.testSuiteResultsId = testSuiteResultsId;
        return this;
    }

    public TestCaseResult build() {
        TestCaseResult testCaseResult = new TestCaseResult(methodName, testSuiteResultsId);
        testCaseResult.setWordify(wordify);
        testCaseResult.setStatus(status);
        return testCaseResult;
    }
}
