package junit5.results.model;

import org.junit.jupiter.api.extension.ExtensionContext;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * <?xml version="1.0" encoding="UTF-8"?>
 * <testsuite name="junit5.learning.parameters.LearningTest" tests="8" skipped="0" failures="0" errors="0" timestamp="2021-03-30T20:03:44" hostname="Jamess-MacBook-Pro.local" time="0.021">
 *   <properties/>
 *   <testcase name="[1] test, TEST" classname="junit5.learning.parameters.LearningTest" time="0.001"/>
 *   <testcase name="[2] tEst, TEST" classname="junit5.learning.parameters.LearningTest" time="0.001"/>
 *   <testcase name="[3] Java, JAVA" classname="junit5.learning.parameters.LearningTest" time="0.001"/>
 *   <testcase name="RepeatingTest 1/5" classname="junit5.learning.parameters.LearningTest" time="0.005"/>
 *   <testcase name="RepeatingTest 2/5" classname="junit5.learning.parameters.LearningTest" time="0.0"/>
 *   <testcase name="RepeatingTest 3/5" classname="junit5.learning.parameters.LearningTest" time="0.001"/>
 *   <testcase name="RepeatingTest 4/5" classname="junit5.learning.parameters.LearningTest" time="0.0"/>
 *   <testcase name="RepeatingTest 5/5" classname="junit5.learning.parameters.LearningTest" time="0.001"/>
 *   <system-out><![CDATA[RepeatingTest 1/5-->1
 * RepeatingTest 2/5-->2
 * RepeatingTest 3/5-->3
 * RepeatingTest 4/5-->4
 * RepeatingTest 5/5-->5
 * ]]></system-out>
 *   <system-err><![CDATA[]]></system-err>
 * </testsuite>
 */
public class TestSuiteResults {
    private final TestSuiteResultsId resultsId;
    private final TestSuiteResultsMetadata resultsMetadata = new TestSuiteResultsMetadata();

    private final List<String> methodNames = new ArrayList<>();
    private final List<TestCaseResult> testCaseResults = new ArrayList<>();
    private final ConcurrentHashMap<ExtensionContext, TestCaseResult> contextToTestResult = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, List<ExtensionContext>> methodNameToContexts = new ConcurrentHashMap<>();

    public TestSuiteResults(TestSuiteResultsId resultsId) {
        this.resultsId = resultsId;
    }

    public TestCaseResult newResultsForTest(ExtensionContext context) {
        resultsMetadata.incrementTest();

        TestCaseResult testCaseResult = testResult(context);
        String methodName = getMethodName(context);
        methodNames.add(methodName);

        if(methodNameToContexts.containsKey(methodName)){
            methodNameToContexts.get(methodName).add(context);
        } else {
            List<ExtensionContext> contexts = new ArrayList<>();
            contexts.add(context);
            methodNameToContexts.put(methodName, contexts);
        }

        testCaseResults.add(testCaseResult);
        contextToTestResult.put(context, testCaseResult);
        return testCaseResult;
    }

    public TestCaseResult getResultsForTest(ExtensionContext context) {
        return contextToTestResult.get(context);
    }

    public TestSuiteResultsId getResultsId() {
        return resultsId;
    }

    public TestSuiteResultsMetadata getResultsMetadata() {
        return resultsMetadata;
    }

    public ConcurrentHashMap<String, List<ExtensionContext>> getMethodNameToContext() {
        return methodNameToContexts;
    }

    public TestCaseResult getCapturedTestMethod(String testMethodName) {
        ExtensionContext extensionContext = methodNameToContexts.get(testMethodName).get(0);
        return contextToTestResult.get(extensionContext);
    }

    public List<String> getMethodNames() {
        return methodNames;
    }

    public List<TestCaseResult> getCapturedTestMethods(String testMethodName) {
        return methodNameToContexts.get(testMethodName).stream()
            .map(contextToTestResult::get)
            .collect(Collectors.toList());
    }

    public ConcurrentHashMap<ExtensionContext, TestCaseResult> getContextToTestResult() {
        return contextToTestResult;
    }

    public List<TestCaseResult> getTestResults() {
        return testCaseResults;
    }

    private String getMethodName(ExtensionContext context) {
        return context.getTestMethod().map(Method::getName).orElse("could-not-get-method-name");
    }

    private TestCaseResult testResult(ExtensionContext context) {
        return new TestCaseResult(getMethodName(context), new TestSuiteResultsId(context.getRequiredTestClass()));
    }
}
