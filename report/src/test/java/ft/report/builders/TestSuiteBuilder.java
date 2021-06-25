package ft.report.builders;

import io.bitsmart.bdd.report.report.model.TestCase;
import io.bitsmart.bdd.report.report.model.TestSuite;
import io.bitsmart.bdd.report.report.model.TestSuiteSummary;

import java.util.List;

public final class TestSuiteBuilder {
    private String name;
    private String className;
    private String packageName;
    private List<String> methodNames;
    private List<TestCase> testCases;
    private TestSuiteSummary summary;

    private TestSuiteBuilder() {
    }

    public static TestSuiteBuilder aTestSuite() {
        return new TestSuiteBuilder();
    }

    public TestSuiteBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public TestSuiteBuilder withClassName(String className) {
        this.className = className;
        return this;
    }

    public TestSuiteBuilder withPackageName(String packageName) {
        this.packageName = packageName;
        return this;
    }

    public TestSuiteBuilder withMethodNames(List<String> methodNames) {
        this.methodNames = methodNames;
        return this;
    }

    public TestSuiteBuilder withTestCases(List<TestCase> testCases) {
        this.testCases = testCases;
        return this;
    }

    public TestSuiteBuilder withSummary(TestSuiteSummary summary) {
        this.summary = summary;
        return this;
    }

    public TestSuite build() {
        return new TestSuite(name, className, packageName, methodNames, testCases, summary);
    }
}
