package io.bitsmart.bdd.report.report.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class TestSuite {
    private final String name;
    private final String className;
    private final String packageName;
    private final List<String> methodNames;
    private final List<TestCase> testCases;
    private final TestSuiteSummary summary;
    // setup, teardown metrics
    // time started
    // time taken

    @JsonCreator
    public TestSuite(
        @JsonProperty("name") String name,
        @JsonProperty("className") String className,
        @JsonProperty("packageName") String packageName,
        @JsonProperty("methodNames") List<String> methodNames,
        @JsonProperty("testResults") List<TestCase> testCases,
        @JsonProperty("summary") TestSuiteSummary summary)
    {
        this.name = name;
        this.className = className;
        this.packageName = packageName;
        this.methodNames = methodNames;
        this.testCases = testCases;
        this.summary = summary;
    }

    public String getName() {
        return name;
    }

    public String getClassName() {
        return className;
    }

    public String getPackageName() {
        return packageName;
    }

    public List<String> getMethodNames() {
        return methodNames;
    }

    public List<TestCase> getTestCases() {
        return testCases;
    }

    public TestSuiteSummary getSummary() {
        return summary;
    }

    @Override
    public String toString() {
        return "TestSuite{" +
            "name='" + name + '\'' +
            ", className='" + className + '\'' +
            ", packageName='" + packageName + '\'' +
            ", methodNames=" + methodNames +
            ", testCases=" + testCases +
            ", summary=" + summary +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TestSuite)) return false;
        TestSuite testSuite = (TestSuite) o;
        return Objects.equals(name, testSuite.name) && Objects.equals(className, testSuite.className) && Objects.equals(packageName, testSuite.packageName) && Objects.equals(methodNames, testSuite.methodNames) && Objects.equals(testCases, testSuite.testCases) && Objects.equals(summary, testSuite.summary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, className, packageName, methodNames, testCases, summary);
    }
}
