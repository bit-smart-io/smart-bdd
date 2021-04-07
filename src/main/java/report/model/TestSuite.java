package report.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TestSuite {
    private final String name;
    private final String className;
    private final String packageName;
    private final List<String> methodNames;
    private final List<TestCase> testCases;

    // setup,tear down metrics
    // time started
    // time taken
    // summary?
    //  passing count

    @JsonCreator
    public TestSuite(
        @JsonProperty("name") String name,
        @JsonProperty("className") String className,
        @JsonProperty("packageName") String packageName,
        @JsonProperty("methodNames") List<String> methodNames,
        @JsonProperty("testResults") List<TestCase> testCases)
    {
        this.name = name;
        this.className = className;
        this.packageName = packageName;
        this.methodNames = methodNames;
        this.testCases = testCases;
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

    public List<TestCase> getTestResults() {
        return testCases;
    }
}
