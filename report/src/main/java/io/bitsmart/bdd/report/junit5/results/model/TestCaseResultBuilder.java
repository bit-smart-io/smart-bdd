package io.bitsmart.bdd.report.junit5.results.model;

import java.util.ArrayList;
import java.util.List;

//TODO move to test or use to create the TestCaseResult
public final class TestCaseResultBuilder {
    private String wordify;
    private TestCaseResultStatus status;
    private String methodName;
    private TestSuiteClass testSuiteClass;
    private List<Object> args = new ArrayList<>();
    private String name;

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

    public TestCaseResultBuilder withTestSuiteClass(TestSuiteClass testSuiteClass) {
        this.testSuiteClass = testSuiteClass;
        return this;
    }

    public TestCaseResultBuilder withArgs(List<Object> args) {
        this.args = args;
        return this;
    }

    public TestCaseResultBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public TestCaseResult build() {
        TestCaseResult testCaseResult = new TestCaseResult(methodName, testSuiteClass);
        testCaseResult.setWordify(wordify);
        testCaseResult.setStatus(status);
        testCaseResult.setArgs(args);
        testCaseResult.setName(name);
        return testCaseResult;
    }
}