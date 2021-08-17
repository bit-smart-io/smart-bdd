package io.bitsmart.bdd.report.junit5.results.model;

import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static io.bitsmart.bdd.report.junit5.results.model.TestSuiteClass.testSuiteClass;

public class TestResults {

    // ConcurrentHashMap<ClassSimpleName, TestSuiteResult> -> ConcurrentHashMap<TestSuiteClass, TestSuiteResult>
    //  ReportExtension after all
    //  - create report TestSuite
    //  - write report TestSuite
    //  - accumulate results Map<TestSuiteClass, TestSuiteResultsMetadata>
    //  - classNameToTestSuiteResults.remove(testSuiteClass)
    //  SmartTestExecutionListener executionFinished
    //  - create report index from TestSuiteResultsMetadata

    private final ConcurrentHashMap<TestSuiteClass, TestSuiteResult> classNameToTestSuiteResults = new ConcurrentHashMap<>();

    public List<TestSuiteClass> getClasses() {
        return Collections.list(classNameToTestSuiteResults.keys());
    }

    public Collection<TestSuiteResult> getTestSuiteResults() {
        return classNameToTestSuiteResults.values();
    }

    public TestSuiteResult getTestSuiteResults(TestSuiteClass testSuiteClass) {
        return classNameToTestSuiteResults.get(testSuiteClass);
    }

    public TestSuiteResult getTestResultsForClass(ExtensionContext extensionContext) {
        return classNameToTestSuiteResults.get(getTestSuiteClass(extensionContext));
    }

    public TestSuiteResult startTestSuite(ExtensionContext context) {
        Class<?> clazz = context.getRequiredTestClass();
        TestSuiteResult testSuiteResult = new TestSuiteResult(testSuiteClass(clazz));
        classNameToTestSuiteResults.put(getTestSuiteClass(context), testSuiteResult);
        return testSuiteResult;
    }

    public TestSuiteClass getTestSuiteClass(ExtensionContext extensionContext) {
        return testSuiteClass(extensionContext.getRequiredTestClass());
    }

    public void reset() {
        classNameToTestSuiteResults.clear();
    }
}
