package report.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ClassResults {
    private final String className;
    private final String packageName;
    private final List<String> methodNames;
    private final List<TestResult> testResults;

    // setup,tear down metrics
    // time started
    // time taken
    // summary?
    //  passing count

    @JsonCreator
    public ClassResults(
        @JsonProperty("className") String className,
        @JsonProperty("packageName") String packageName,
        @JsonProperty("methodNames") List<String> methodNames,
        @JsonProperty("testResults") List<TestResult> testResults)
    {
        this.className = className;
        this.packageName = packageName;
        this.methodNames = methodNames;
        this.testResults = testResults;
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

    public List<TestResult> getTestResults() {
        return testResults;
    }
}
