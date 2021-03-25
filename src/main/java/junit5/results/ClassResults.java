package junit5.results;

import org.junit.jupiter.api.extension.ExtensionContext;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ClassResults {
    private final List<String> methodNames = new ArrayList<>();
    private final ConcurrentHashMap<ExtensionContext, TestResult> contextToTestResult = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, List<ExtensionContext>> methodNameToContexts = new ConcurrentHashMap<>();

    public TestResult newResultsForTest(ExtensionContext context) {
        TestResult testResult = new TestResult();
        String methodName = getMethodName(context);
        methodNames.add(methodName);

        if(methodNameToContexts.containsKey(methodName)){
            methodNameToContexts.get(methodName).add(context);
        } else {
            List<ExtensionContext> contexts = new ArrayList<>();
            contexts.add(context);
            methodNameToContexts.put(methodName, contexts);
        }

        contextToTestResult.put(context, testResult);
        return testResult;
    }

    public TestResult getResultsForTest(ExtensionContext context) {
        return contextToTestResult.get(context);
    }

    private String getMethodName(ExtensionContext context) {
        return context.getTestMethod().map(Method::getName).orElse("could-not-get-method-name");
    }

    public ConcurrentHashMap<String, List<ExtensionContext>> getMethodNameToContext() {
        return methodNameToContexts;
    }

    public TestResult getCapturedTestMethod(String testMethodName) {
        ExtensionContext extensionContext = methodNameToContexts.get(testMethodName).get(0);
        return contextToTestResult.get(extensionContext);
    }

    public List<String> getMethodNames() {
        return methodNames;
    }

    public List<TestResult> getCapturedTestMethods(String testMethodName) {
        return methodNameToContexts.get(testMethodName).stream()
            .map(context -> contextToTestResult.get(context))
            .collect(Collectors.toList());
    }

    public ConcurrentHashMap<ExtensionContext, TestResult> getContextToTestResult() {
        return contextToTestResult;
    }
}
