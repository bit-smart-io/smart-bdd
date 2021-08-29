package io.bitsmart.bdd.report.junit5.results.model;

import io.bitsmart.bdd.report.junit5.results.model.notes.Notes;
import io.bitsmart.wordify.WordifyString;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static io.bitsmart.bdd.report.junit5.results.model.TestSuiteClass.testSuiteClass;

public class TestResults {

    /**
     * Performance improvement.
     * usage:
     * <p>
     * - For each test suite
     * -- write TestSuite
     * -- updateTestSuiteResultsMetadata
     * -- removeTestSuite
     * - After all tests are complete, create report index from all the TestSuiteResultsMetadata
     * <p>
     * private final ConcurrentHashMap<TestSuiteClass, TestSuiteResultsMetadata> testSuiteToTestSuiteResultsMetadata = new ConcurrentHashMap<>();
     * <p>
     * public void removeTestSuite(TestSuiteClass testSuiteClass) {
     * testSuiteToTestSuiteResults.remove(testSuiteClass);
     * }
     * <p>
     * public void updateTestSuiteResultsMetadata(TestSuiteClass testSuiteClass) {
     * testSuiteToTestSuiteResultsMetadata.put(testSuiteClass, testSuiteToTestSuiteResults.get(testSuiteClass).getMetadata());
     * }
     */

    private final ConcurrentHashMap<TestSuiteClass, TestSuiteResult> testSuiteToTestSuiteResults = new ConcurrentHashMap<>();

    public List<TestSuiteClass> getClasses() {
        return Collections.list(testSuiteToTestSuiteResults.keys());
    }

    public Collection<TestSuiteResult> getTestSuiteResults() {
        return testSuiteToTestSuiteResults.values();
    }

    public TestSuiteResult getTestSuiteResults(TestSuiteClass testSuiteClass) {
        return testSuiteToTestSuiteResults.get(testSuiteClass);
    }

    public TestSuiteResult getTestResultsForClass(ExtensionContext extensionContext) {
        return testSuiteToTestSuiteResults.get(getTestSuiteClass(extensionContext));
    }

    public TestSuiteResult startTestSuite(ExtensionContext context) {
        TestSuiteClass testSuiteClass = testSuiteClass(context.getRequiredTestClass());
        String title = new WordifyString(testSuiteClass.getClassName()).wordify();
        TestSuiteResult testSuiteResult = new TestSuiteResult(testSuiteClass, title, new Notes());
        testSuiteToTestSuiteResults.put(getTestSuiteClass(context), testSuiteResult);
        return testSuiteResult;
    }

    public TestSuiteClass getTestSuiteClass(ExtensionContext extensionContext) {
        return testSuiteClass(extensionContext.getRequiredTestClass());
    }

    public void reset() {
        testSuiteToTestSuiteResults.clear();
    }
}
