package junit5.results;

import java.util.Objects;

public class TestResult {
    private String wordify;
    private Status status;
    private final String methodName;
    private final String className;
    private final String packageName;

    public enum Status {
        PASSED,
        FAILED,
    }

    public TestResult(String methodName, String className, String packageName) {
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

    public void setWordify(String wordify) {
        this.wordify = wordify;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TestResult{" +
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
        TestResult that = (TestResult) o;
        return Objects.equals(wordify, that.wordify) && status == that.status && Objects.equals(methodName, that.methodName) && Objects.equals(className, that.className) && Objects.equals(packageName, that.packageName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wordify, status, methodName, className, packageName);
    }
}
