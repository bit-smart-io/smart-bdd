package ft.report.builders;

import io.bitsmart.bdd.report.report.model.TestSuite;
import io.bitsmart.bdd.report.utils.Builder;
import io.bitsmart.bdd.report.utils.BuilderUtils;

import java.util.ArrayList;
import java.util.List;

import static ft.report.builders.TestSuiteSummaryBuilder.aTestSuiteSummary;

public final class TestSuiteBuilder implements Builder<TestSuite> {
    private String name;
    private String className;
    private String packageName;
    private final List<String> methodNames = new ArrayList<>();
    private final List<TestCaseBuilder> testCases = new ArrayList<>();;
    private TestSuiteSummaryBuilder summary = aTestSuiteSummary();

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
        this.methodNames.clear();
        this.methodNames.addAll(methodNames);
        return this;
    }

    public TestSuiteBuilder withTestCases(List<TestCaseBuilder> testCases) {
        this.testCases.clear();
        this.testCases.addAll(testCases);
        return this;
    }

    public TestSuiteBuilder withSummary(TestSuiteSummaryBuilder summary) {
        this.summary = summary;
        return this;
    }

    public TestSuite build() {
        return new TestSuite(name, className, packageName, methodNames, BuilderUtils.build(testCases), summary.build());
    }
}
