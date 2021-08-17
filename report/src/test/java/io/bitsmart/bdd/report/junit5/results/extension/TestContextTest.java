package io.bitsmart.bdd.report.junit5.results.extension;

import io.bitsmart.bdd.report.junit5.results.model.TestCaseNameFactory;
import io.bitsmart.bdd.report.junit5.results.model.TestResults;
import io.bitsmart.bdd.report.junit5.results.model.TestSuiteResult;
import io.bitsmart.bdd.report.report.ReportWriter;
import io.bitsmart.wordify.WordifyExtensionContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.Arrays;
import java.util.Collection;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TestContextTest {

    private final TestResults testResults = mock(TestResults.class);
    private final WordifyExtensionContext wordifyExtensionContext = mock(WordifyExtensionContext.class);
    private final TestCaseNameFactory testCaseNameFactory = mock(TestCaseNameFactory.class);
    private final ReportWriter reportWriter = mock(ReportWriter.class);

    private final TestContext testContext = new TestContext(
        testResults,
        wordifyExtensionContext,
        testCaseNameFactory,
        reportWriter
    );

    @Test
    void afterAllWritesTestSuite() throws Exception {
        ExtensionContext extensionContext = mock(ExtensionContext.class);
        TestSuiteResult testSuiteResult = mock(TestSuiteResult.class);

        Collection<TestSuiteResult> testSuiteResults = Arrays.asList(testSuiteResult);
        when(testResults.getTestResultsForClass(extensionContext)).thenReturn(testSuiteResult);
        when(testResults.getTestSuiteResults()).thenReturn(testSuiteResults);

        testContext.afterAll(extensionContext);

        //verify(reportWriter).write(testSuiteResult);
    }
}