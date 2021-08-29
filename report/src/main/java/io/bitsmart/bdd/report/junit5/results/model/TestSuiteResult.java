package io.bitsmart.bdd.report.junit5.results.model;

import io.bitsmart.bdd.report.junit5.results.model.notes.Notes;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static io.bitsmart.bdd.report.junit5.results.model.TestSuiteClass.testSuiteClass;
import static io.bitsmart.bdd.report.junit5.results.model.TestMethod.testMethod;

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
 *
 * This could be a service to create an immutable TestSuiteResults??
 */
public class TestSuiteResult {
    private final TestSuiteClass testSuiteClass;
    private final List<TestMethod> methods = new ArrayList<>();

    /** all results including different params and or repeated test */
    private final List<TestCaseResult> testCaseResults = new ArrayList<>();
    private final ConcurrentHashMap<ExtensionContext, TestCaseResult> contextToTestCaseResult = new ConcurrentHashMap<>();

    /**
     * This seems to be only used for tests!!
     * could this be test instance to params/repeat i.e. Method to List<ExtensionContext>
     * paramTest(int i)
     * paramTest(int i, String s)
     * method name is paramTest. But to uniquely identify it should be Method?
     */
    private final ConcurrentHashMap<TestMethod, List<ExtensionContext>> methodToContexts = new ConcurrentHashMap<>();

    private final String title;

    private final Notes notes;

    private TestSuiteResultsMetadata metadata;

    public TestSuiteResult(TestSuiteClass testSuiteClass, String title, Notes notes) {
        this.testSuiteClass = testSuiteClass;
        this.title = title;
        this.notes = notes;
    }

    public TestCaseResult startTestCase(ExtensionContext context) {
        TestCaseResult testCaseResult = createTestCaseResult(context);
        TestMethod method = method(context);
        methods.add(method);

        if (methodToContexts.containsKey(method)) {
            methodToContexts.get(method).add(context);
        } else {
            List<ExtensionContext> contexts = new ArrayList<>();
            contexts.add(context);
            methodToContexts.put(method, contexts);
        }

        this.testCaseResults.add(testCaseResult);
        contextToTestCaseResult.put(context, testCaseResult);
        return testCaseResult;
    }

    public void completeTestSuite() {
        metadata = TestSuiteResultsMetadataFactory.create(testCaseResults);
    }

    public TestCaseResult getTestCaseResult(ExtensionContext context) {
        return contextToTestCaseResult.get(context);
    }

    /** only used for testing. Prod code uses getTestCaseResults */
    public TestCaseResult getTestCaseResult(TestMethod method) {
        ExtensionContext extensionContext = methodToContexts.get(method).get(0);
        return contextToTestCaseResult.get(extensionContext);
    }

    /** only used for testing. Prod code uses getTestCaseResults */
    public List<TestCaseResult> getTestCaseResults(TestMethod method) {
        return methodToContexts.get(method).stream()
            .map(contextToTestCaseResult::get)
            .collect(Collectors.toList());
    }

    public String getTitle() {
        return title;
    }

    public TestSuiteClass getTestSuiteClass() {
        return testSuiteClass;
    }

    public TestSuiteResultsMetadata getMetadata() {
        return metadata;
    }

    public ConcurrentHashMap<TestMethod, List<ExtensionContext>> getMethodNameToContext() {
        return methodToContexts;
    }

    public List<TestMethod> getMethods() {
        return methods;
    }

    public List<TestCaseResult> getTestCaseResults() {
        return testCaseResults;
    }

    public Notes getNotes() {
        return notes;
    }

    private TestMethod method(ExtensionContext context) {
        return testMethod(context.getTestMethod().orElse(null));
    }

    private TestCaseResult createTestCaseResult(ExtensionContext context) {
        return new TestCaseResult(method(context), testSuiteClass(context.getRequiredTestClass()), new Notes());
    }
}
