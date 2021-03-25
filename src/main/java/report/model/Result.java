package report.model;

import java.util.Objects;

/**
 * Name ideas: ReportItem, Item, ResultItem
 * java.lang.reflect.Parameter
 * java.lang.reflect.Method#getParameterTypes()
 */
public class Result {
    private final String wordify;
    private final Status status;
    private final String methodName;
    private final String className;
    private final String packageName;
    //private final List<String> parameters;
    // time started
    // time taken

    public Result(
        String wordify,
        Status status,
        String methodName,
        String className,
        String packageName)
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
        if (!(o instanceof Result)) return false;
        Result result = (Result) o;
        return Objects.equals(wordify, result.wordify) && status == result.status && Objects.equals(methodName, result.methodName) && Objects.equals(className, result.className) && Objects.equals(packageName, result.packageName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wordify, status, methodName, className, packageName);
    }
}
