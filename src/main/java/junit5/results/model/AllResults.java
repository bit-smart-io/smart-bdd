package junit5.results.model;

import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static junit5.results.model.TestSuiteResultsId.testSuiteResultsId;

/**
 * This concept could be generified.
 * We could have:
 * - Collector
 *   - ResultsForClass for timing, status, wordify etc...
 *   - ResultsForTest for timing, status, wordify etc...
 *
 *   TimedResult implements Result
 *   - start
 *   - end
 *   - total
 *   This could be applied to:
 *   - ResultsForClass how long did the whole class take
 *   - ResultsForTest how long did the test take
 *
 *   TODO same test with different args?
 *
 *   Should we use domain objects?
 *   ClassName extends String
 *   ClassName { String name }
 *   ClassName.from(name)
 *   className(name)
 */
public class AllResults {
    private final ConcurrentHashMap<String, TestSuiteResults> classNameToTestSuiteResults = new ConcurrentHashMap<>();

    public List<String> getClasses() {
        return Collections.list(classNameToTestSuiteResults.keys());
    }

    public ConcurrentHashMap<String, TestSuiteResults> getClassNameToTestSuiteResults() {
        return classNameToTestSuiteResults;
    }

    public TestSuiteResults getTestResultsForClass(ExtensionContext extensionContext) {
        return classNameToTestSuiteResults.get(getClassName(extensionContext));
    }

    public TestSuiteResults newTestSuiteResults(ExtensionContext context) {
        Class<?> clazz = context.getRequiredTestClass();
        TestSuiteResults testSuiteResults = new TestSuiteResults(testSuiteResultsId(clazz));
        classNameToTestSuiteResults.put(getClassName(context), testSuiteResults);
        return testSuiteResults;
    }

    public String getClassName(ExtensionContext extensionContext) {
        return extensionContext.getRequiredTestClass().getSimpleName();
    }

    public void reset() {
        classNameToTestSuiteResults.clear();
    }
}
