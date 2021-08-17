package io.bitsmart.bdd.report.junit5.results.model;

import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static io.bitsmart.bdd.report.junit5.results.model.ClassSimpleName.classSimpleName;
import static io.bitsmart.bdd.report.junit5.results.model.TestSuiteClass.testSuiteClass;

public class TestResults {
    private final ConcurrentHashMap<ClassSimpleName, TestSuiteResult> classNameToTestSuiteResults = new ConcurrentHashMap<>();

    public List<ClassSimpleName> getClasses() {
        return Collections.list(classNameToTestSuiteResults.keys());
    }

    public Collection<TestSuiteResult> getTestSuiteResults() {
        return classNameToTestSuiteResults.values();
    }

    public TestSuiteResult getTestSuiteResults(ClassSimpleName className) {
        return classNameToTestSuiteResults.get(className);
    }

    public TestSuiteResult getTestResultsForClass(ExtensionContext extensionContext) {
        return classNameToTestSuiteResults.get(getClassName(extensionContext));
    }

    public TestSuiteResult startTestSuite(ExtensionContext context) {
        Class<?> clazz = context.getRequiredTestClass();
        TestSuiteResult testSuiteResult = new TestSuiteResult(testSuiteClass(clazz));
        classNameToTestSuiteResults.put(getClassName(context), testSuiteResult);
        return testSuiteResult;
    }

    public ClassSimpleName getClassName(ExtensionContext extensionContext) {
        return classSimpleName(extensionContext.getRequiredTestClass());
    }

    public void reset() {
        classNameToTestSuiteResults.clear();
    }
}
