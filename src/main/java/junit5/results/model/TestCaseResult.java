package junit5.results.model;

import java.util.Objects;

public class TestCaseResult {
    private String wordify;
    private TestCaseStatus status;
    private Throwable cause;
    private final String methodName;
    private final TestSuiteResultsId testSuiteResultsId;

    public TestCaseResult(String methodName, TestSuiteResultsId testSuiteResultsId) {
        this.methodName = methodName;
        this.testSuiteResultsId = testSuiteResultsId;
    }

    public String getWordify() {
        return wordify;
    }

    public TestCaseStatus getStatus() {
        return status;
    }

    public Throwable getCause() {
        return cause;
    }

    public String getMethodName() {
        return methodName;
    }

    public TestSuiteResultsId getTestSuiteResultsId() {
        return testSuiteResultsId;
    }

    public TestCaseResult setWordify(String wordify) {
        this.wordify = wordify;
        return this;
    }

    public TestCaseResult setStatus(TestCaseStatus status) {
        this.status = status;
        return this;
    }

    public TestCaseResult setCause(Throwable cause) {
        this.cause = cause;
        return this;
    }

    /** does not include cause */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TestCaseResult)) return false;
        TestCaseResult that = (TestCaseResult) o;
        return Objects.equals(wordify, that.wordify) && status == that.status && Objects.equals(methodName, that.methodName) && Objects.equals(testSuiteResultsId, that.testSuiteResultsId);
    }

    /** does not include cause */
    @Override
    public int hashCode() {
        return Objects.hash(wordify, status, methodName, testSuiteResultsId);
    }

    /** does not include cause */
    @Override
    public String toString() {
        return "TestCaseResult{" +
            "wordify='" + wordify + '\'' +
            ", status=" + status +
            ", methodName='" + methodName + '\'' +
            ", testSuiteResultsId=" + testSuiteResultsId +
            '}';
    }
}
