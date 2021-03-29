package report.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * Name ideas: ReportItem, Item, ResultItem
 * java.lang.reflect.Parameter
 * java.lang.reflect.Method#getParameterTypes()
 */
public class TestResult {
    private final String wordify;
    private final Status status;
    private final String methodName;
    private final String className;
    private final String packageName;
    //private final List<String> parameters;
    // time started
    // time taken

    @JsonCreator
    public TestResult(
        @JsonProperty("wordify")  String wordify,
        @JsonProperty("status")  Status status,
        @JsonProperty("methodName")  String methodName,
        @JsonProperty("className")  String className,
        @JsonProperty("packageName")  String packageName)
    {
        this.wordify = wordify;
        this.status = status;
        this.methodName = methodName;
        this.className = className;
        this.packageName = packageName;
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

    public String getClassName() {
        return className;
    }

    public String getPackageName() {
        return packageName;
    }

    @Override
    public String toString() {
        return "Result{" +
            "wordify='" + wordify + '\'' +
            ", status=" + status +
            ", methodName='" + methodName + '\'' +
            ", className='" + className + '\'' +
            ", packageName='" + packageName + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TestResult)) return false;
        TestResult testResult = (TestResult) o;
        return Objects.equals(wordify, testResult.wordify) && status == testResult.status && Objects.equals(methodName, testResult.methodName) && Objects.equals(className, testResult.className) && Objects.equals(packageName, testResult.packageName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wordify, status, methodName, className, packageName);
    }
}
