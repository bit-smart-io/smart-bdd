package results;

import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ResultsForClass {
    private List<String> methodNames = new ArrayList<>();
    private ConcurrentHashMap<ExtensionContext, ResultsForTest> contextToResultsForTest = new ConcurrentHashMap();
    private ConcurrentHashMap<String, List<ExtensionContext>> methodNameToContext = new ConcurrentHashMap();

    public ResultsForTest newResultsForTest(ExtensionContext context) {
        ResultsForTest resultsForTest = new ResultsForTest();
        String methodName = getMethodName(context);
        methodNames.add(methodName);

        if(methodNameToContext.containsKey(methodName)){
            methodNameToContext.get(methodName).add(context);
        } else {
            List<ExtensionContext> contexts = new ArrayList<>();
            contexts.add(context);
            methodNameToContext.put(methodName, contexts);
        }

        contextToResultsForTest.put(context, resultsForTest);
        return resultsForTest;
    }

    public ResultsForTest getResultsForTest(ExtensionContext context) {
        return contextToResultsForTest.get(context);
    }

    private String getMethodName(ExtensionContext context) {
        return context.getTestMethod().map(m -> m.getName()).orElse("could-not-get-method-name");
    }

    public ConcurrentHashMap<String, List<ExtensionContext>> getMethodNameToContext() {
        return methodNameToContext;
    }

    public ResultsForTest getCapturedTestMethod(String testMethodName) {
        ExtensionContext extensionContext = methodNameToContext.get(testMethodName).get(0);
        return contextToResultsForTest.get(extensionContext);
    }

    public List<String> getMethodNames() {
        return methodNames;
    }

    public List<ResultsForTest> getCapturedTestMethods(String testMethodName) {
        return methodNameToContext.get(testMethodName).stream()
            .map(context -> contextToResultsForTest.get(context))
            .collect(Collectors.toList());
    }

    public ConcurrentHashMap<ExtensionContext, ResultsForTest> getContextToResultsForTest() {
        return contextToResultsForTest;
    }
}
