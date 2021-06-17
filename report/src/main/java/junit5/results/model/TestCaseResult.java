package junit5.results.model;

import java.util.Objects;
import java.util.Optional;

public class TestCaseResult {
    private String wordify;
    private TestCaseResultStatus status;
    private Throwable cause;
    private final String methodName;
    private final TestSuiteClass testSuiteClass;

    public TestCaseResult(String methodName, TestSuiteClass testSuiteClass) {
        this.methodName = methodName;
        this.testSuiteClass = testSuiteClass;
    }

    public String getWordify() {
        return wordify;
    }

    public TestCaseResultStatus getStatus() {
        return status;
    }

    public Optional<Throwable> getCause() {
        return Optional.ofNullable(cause);
    }

    public String getMethodName() {
        return methodName;
    }

    public TestSuiteClass getTestSuiteResultsId() {
        return testSuiteClass;
    }

    public TestCaseResult setWordify(String wordify) {
        this.wordify = wordify;
        return this;
    }

    public TestCaseResult setStatus(TestCaseResultStatus status) {
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
        return Objects.equals(wordify, that.wordify) && status == that.status && Objects.equals(methodName, that.methodName) && Objects.equals(testSuiteClass, that.testSuiteClass);
    }

    /** does not include cause */
    @Override
    public int hashCode() {
        return Objects.hash(wordify, status, methodName, testSuiteClass);
    }

    /** does not include cause */
    @Override
    public String toString() {
        return "TestCaseResult{" +
            "wordify='" + wordify + '\'' +
            ", status=" + status +
            ", methodName='" + methodName + '\'' +
            ", testSuiteClass=" + testSuiteClass +
            '}';
    }
}
