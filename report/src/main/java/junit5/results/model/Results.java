package junit5.results.model;

import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static junit5.results.model.ClassSimpleName.classSimpleName;
import static junit5.results.model.TestSuiteClass.testSuiteResultsId;

public class Results {
    private final ConcurrentHashMap<ClassSimpleName, TestSuiteResults> classNameToTestSuiteResults = new ConcurrentHashMap<>();

    public List<ClassSimpleName> getClasses() {
        return Collections.list(classNameToTestSuiteResults.keys());
    }

    public Collection<TestSuiteResults> getTestSuiteResults() {
        return classNameToTestSuiteResults.values();
    }

    public TestSuiteResults getTestSuiteResults(ClassSimpleName className) {
        return classNameToTestSuiteResults.get(className);
    }

    public TestSuiteResults getTestResultsForClass(ExtensionContext extensionContext) {
        return classNameToTestSuiteResults.get(getClassName(extensionContext));
    }

    public TestSuiteResults startTestSuite(ExtensionContext context) {
        Class<?> clazz = context.getRequiredTestClass();
        TestSuiteResults testSuiteResults = new TestSuiteResults(testSuiteResultsId(clazz));
        classNameToTestSuiteResults.put(getClassName(context), testSuiteResults);
        return testSuiteResults;
    }

    public ClassSimpleName getClassName(ExtensionContext extensionContext) {
        return classSimpleName(extensionContext.getRequiredTestClass());
    }

    public void reset() {
        classNameToTestSuiteResults.clear();
    }
}
