package junit5.results.model;

//TODO move to test
public final class TestCaseResultBuilder {
    private String wordify;
    private TestCaseResultStatus status;
    private String methodName;
    private TestSuiteClass testSuiteClass;

    private TestCaseResultBuilder() {
    }

    public static TestCaseResultBuilder aTestCaseResult() {
        return new TestCaseResultBuilder();
    }

    public TestCaseResultBuilder withWordify(String wordify) {
        this.wordify = wordify;
        return this;
    }

    public TestCaseResultBuilder withStatus(TestCaseResultStatus status) {
        this.status = status;
        return this;
    }

    public TestCaseResultBuilder withMethodName(String methodName) {
        this.methodName = methodName;
        return this;
    }

    public TestCaseResultBuilder withTestSuiteResultsId(TestSuiteClass testSuiteClass) {
        this.testSuiteClass = testSuiteClass;
        return this;
    }

    public TestCaseResult build() {
        TestCaseResult testCaseResult = new TestCaseResult(methodName, testSuiteClass);
        testCaseResult.setWordify(wordify);
        testCaseResult.setStatus(status);
        return testCaseResult;
    }
}
