package io.bitsmart.bdd.ft.report.ports.json.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * Name ideas: ReportItem, Item, ResultItem
 * java.lang.reflect.Parameter
 * java.lang.reflect.Method#getParameterTypes()
 */
public class TestCase {
    private final String wordify;
    private final Status status;
    private final String name;
    private final String methodName;
    private final String className;
    private final String packageName;
    //private final List<String> parameters;
    // time started
    // time taken

    @JsonCreator
    public TestCase(
        @JsonProperty("wordify")  String wordify,
        @JsonProperty("status") Status status,
        @JsonProperty("name")  String name,
        @JsonProperty("methodName")  String methodName,
        @JsonProperty("className")  String className,
        @JsonProperty("packageName")  String packageName)
    {
        this.wordify = wordify;
        this.status = status;
        this.name = name;
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

    public String getName() {
        return name;
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
        return "TestCase{" +
            "wordify='" + wordify + '\'' +
            ", status=" + status +
            ", name='" + name + '\'' +
            ", methodName='" + methodName + '\'' +
            ", className='" + className + '\'' +
            ", packageName='" + packageName + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TestCase)) return false;
        TestCase testCase = (TestCase) o;
        return Objects.equals(wordify, testCase.wordify) && status == testCase.status && Objects.equals(name, testCase.name) && Objects.equals(methodName, testCase.methodName) && Objects.equals(className, testCase.className) && Objects.equals(packageName, testCase.packageName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wordify, status, name, methodName, className, packageName);
    }
}