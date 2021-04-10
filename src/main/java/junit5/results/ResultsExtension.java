package junit5.results;

import junit5.results.model.TestSuiteResults;
import junit5.results.model.AllResults;
import junit5.results.model.TestCaseResult;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.InvocationInterceptor;
import org.junit.jupiter.api.extension.ReflectiveInvocationContext;
import org.junit.jupiter.api.extension.TestWatcher;
import wordify.WordifyExtensionContext;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Optional;

import static junit5.results.model.TestCaseStatus.PASSED;

/**
 * Potentially we can add - BeforeTestExecutionCallback, AfterTestExecutionCallback
 */
public class ResultsExtension implements
    BeforeAllCallback, BeforeEachCallback, AfterAllCallback, AfterEachCallback, TestWatcher, InvocationInterceptor {
    private static final AllResults allResults = new AllResults();
    private static final WordifyExtensionContext wordifyExtensionContext = new WordifyExtensionContext();

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        TestSuiteResults testSuiteResults = allResults.newResultsForClass(context);
    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        TestSuiteResults testSuiteResults = allResults.getTestResultsForClass(context);
        TestCaseResult testCaseResult = testSuiteResults.newResultsForTest(context);
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        //ResultsForClass captureTestClass = testResultCollector.afterAll(context);
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        //ResultsForClass captureTestClass = testResultCollector.afterEach(context);
    }

    @Override
    public <T> T interceptTestClassConstructor(Invocation<T> invocation, ReflectiveInvocationContext<Constructor<T>> invocationContext, ExtensionContext extensionContext) throws Throwable {
        return invocation.proceed();
    }

    @Override
    public void interceptBeforeAllMethod(Invocation<Void> invocation, ReflectiveInvocationContext<Method> invocationContext, ExtensionContext extensionContext) throws Throwable {
        invocation.proceed();
    }

    @Override
    public void interceptBeforeEachMethod(Invocation<Void> invocation, ReflectiveInvocationContext<Method> invocationContext, ExtensionContext extensionContext) throws Throwable {
        invocation.proceed();
    }

    @Override
    public void interceptTestMethod(Invocation<Void> invocation, ReflectiveInvocationContext<Method> invocationContext, ExtensionContext extensionContext) throws Throwable {
        wordify(invocationContext, extensionContext, "interceptTestMethod");
        invocation.proceed();
    }

    @Override
    public <T> T interceptTestFactoryMethod(Invocation<T> invocation, ReflectiveInvocationContext<Method> invocationContext, ExtensionContext extensionContext) throws Throwable {
        wordify(invocationContext, extensionContext, "interceptTestFactoryMethod");
        return invocation.proceed();
    }

    @Override
    public void interceptTestTemplateMethod(Invocation<Void> invocation, ReflectiveInvocationContext<Method> invocationContext, ExtensionContext extensionContext) throws Throwable {
        wordify(invocationContext, extensionContext, "interceptTestTemplateMethod");
        invocation.proceed();
    }

    @Override
    public void interceptDynamicTest(Invocation<Void> invocation, ExtensionContext extensionContext) throws Throwable {
        invocation.proceed();
    }

    @Override
    public void interceptAfterEachMethod(Invocation<Void> invocation, ReflectiveInvocationContext<Method> invocationContext, ExtensionContext extensionContext) throws Throwable {
        invocation.proceed();
    }

    @Override
    public void interceptAfterAllMethod(Invocation<Void> invocation, ReflectiveInvocationContext<Method> invocationContext, ExtensionContext extensionContext) throws Throwable {
        invocation.proceed();
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        TestCaseResult testCaseResult = getTestResultsForTest(context);
        testCaseResult.setStatus(PASSED);
    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        TestSuiteResults testSuiteResults = allResults.getTestResultsForClass(context);
        TestCaseResult testCaseResult = testSuiteResults.newResultsForTest(context);
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        /* no-op */
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        /* no-op */
    }

    public static AllResults getAllResults() {
        return allResults;
    }

    private TestSuiteResults getTestResultsForClass(ExtensionContext context) {
        return allResults.getTestResultsForClass(context);
    }

    private TestCaseResult getTestResultsForTest(ExtensionContext context) {
        TestSuiteResults testSuiteResults = allResults.getTestResultsForClass(context);
        return testSuiteResults.getResultsForTest(context);
    }

    private void wordify(ReflectiveInvocationContext<Method> invocationContext, ExtensionContext extensionContext, String methodName) {
        TestCaseResult testCaseResult = getTestResultsForTest(extensionContext);
        wordifyExtensionContext.wordify(extensionContext, invocationContext.getArguments()).ifPresent(testCaseResult::setWordify);
//        System.out.println(methodName + ", method: " + extensionContext.getTestMethod() + ", wordify: " + wordify);
    }

    public static void reset() {
        allResults.reset();
    }
}
