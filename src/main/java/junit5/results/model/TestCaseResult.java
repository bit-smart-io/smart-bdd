package junit5.results.model;

import java.util.Objects;

public class TestCaseResult {
    private String wordify;
    private Status status;
    private final String methodName;
    private final TestSuiteResultsId testSuiteResultsId;

    public enum Status {
        PASSED,
        FAILED,
    }

    public TestCaseResult(String methodName, TestSuiteResultsId testSuiteResultsId) {
        this.methodName = methodName;
        this.testSuiteResultsId = testSuiteResultsId;
    }

    public String getWordify() {
        return wordify;
    }

    public Status getStatus() {
        return status;
    }

    public String getMethodName() {
        return methodName;
    }

    public TestSuiteResultsId getTestSuiteResultsId() {
        return testSuiteResultsId;
    }

    public void setWordify(String wordify) {
        this.wordify = wordify;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TestCaseResult)) return false;
        TestCaseResult that = (TestCaseResult) o;
        return Objects.equals(wordify, that.wordify) && status == that.status && Objects.equals(methodName, that.methodName) && Objects.equals(testSuiteResultsId, that.testSuiteResultsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wordify, status, methodName, testSuiteResultsId);
    }

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
