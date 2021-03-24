package report;

/**
 * Name ideas: ReportItem, Item, ResultItem
 * java.lang.reflect.Parameter
 * java.lang.reflect.Method#getParameterTypes()
 */
public class Result {
    private final String methodName;
    private final String className;
    private final String packageName;
    //private final List<String> parameters;
    // time started
    // time taken

    private final String wordify;
    private final Status status;

    public Result(
        String wordify,
        Status status,
        String methodName,
        String className,
        String packageName
        )
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

    public String getClassName() {
        return className;
    }

    public String getPackageName() {
        return packageName;
    }
}
