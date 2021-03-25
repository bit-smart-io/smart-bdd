package report.model;

public final class ResultBuilder {
    private String wordify;
    private Status status;
    private String methodName;
    private String className;
    private String packageName;

    private ResultBuilder() {
    }

    public static ResultBuilder aResult() {
        return new ResultBuilder();
    }

    public ResultBuilder withWordify(String wordify) {
        this.wordify = wordify;
        return this;
    }

    public ResultBuilder withStatus(Status status) {
        this.status = status;
        return this;
    }

    public ResultBuilder withMethodName(String methodName) {
        this.methodName = methodName;
        return this;
    }

    public ResultBuilder withClassName(String className) {
        this.className = className;
        return this;
    }

    public ResultBuilder withPackageName(String packageName) {
        this.packageName = packageName;
        return this;
    }

    public Result build() {
        return new Result(wordify, status, methodName, className, packageName);
    }
}
