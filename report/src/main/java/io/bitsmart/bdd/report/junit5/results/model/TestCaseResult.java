package io.bitsmart.bdd.report.junit5.results.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * TODO remove temp note.
 * how to handle repeats?
 * add method. with grouping, method needs to group by meth+sig+args+repeat.
 * private TestSuiteMethodInvocation method; //args, repeat, etc...
 */
public class TestCaseResult {
    private String wordify;
    private TestCaseResultStatus status;
    private Throwable cause;
    private List<Object> args = new ArrayList<>();
    private String name;
    private TestMethod method;
    private final TestSuiteClass testSuiteClass;

    public TestCaseResult(TestMethod method, TestSuiteClass testSuiteClass) {
        this.method = method;
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

    public String getName() {
        return name;
    }

    public TestMethod getMethod() {
        return method;
    }

    public TestSuiteClass getTestSuiteClass() {
        return testSuiteClass;
    }

    public List<Object> getArgs() {
        return args;
    }

    public TestCaseResult setWordify(String wordify) {
        this.wordify = wordify;
        return this;
    }

    public TestCaseResult setName(String name) {
        this.name = name;
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

    public TestCaseResult setArgs(List<Object> args) {
        this.args = args;
        return this;
    }

    /**
     * does not include cause
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TestCaseResult)) return false;
        TestCaseResult that = (TestCaseResult) o;
        return Objects.equals(wordify, that.wordify) && status == that.status && Objects.equals(args, that.args) && Objects.equals(name, that.name) && Objects.equals(method, that.method) && Objects.equals(testSuiteClass, that.testSuiteClass);
    }

    /**
     * does not include cause
     */
    @Override
    public int hashCode() {
        return Objects.hash(wordify, status, args, name, method, testSuiteClass);
    }

    /**
     * does not include cause
     */
    @Override
    public String toString() {
        return "TestCaseResult{" +
            "wordify='" + wordify + '\'' +
            ", status=" + status +
            ", cause=" + cause +
            ", args=" + args +
            ", name='" + name + '\'' +
            ", method='" + method + '\'' +
            ", testSuiteClass=" + testSuiteClass +
            '}';
    }
}
