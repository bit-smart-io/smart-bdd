package io.bitsmart.bdd.report.junit5.results.extension;

import io.bitsmart.bdd.report.junit5.results.model.TestCaseNameFactory;
import io.bitsmart.bdd.report.junit5.results.model.TestCaseResult;
import io.bitsmart.bdd.report.junit5.results.model.TestResults;
import io.bitsmart.bdd.report.junit5.results.model.TestSuiteResult;
import io.bitsmart.bdd.report.report.ReportWriter;
import io.bitsmart.wordify.WordifyExtensionContext;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.InvocationInterceptor;
import org.junit.jupiter.api.extension.ReflectiveInvocationContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;

import static io.bitsmart.bdd.report.junit5.results.model.TestCaseResultStatus.ABORTED;
import static io.bitsmart.bdd.report.junit5.results.model.TestCaseResultStatus.DISABLED;
import static io.bitsmart.bdd.report.junit5.results.model.TestCaseResultStatus.FAILED;
import static io.bitsmart.bdd.report.junit5.results.model.TestCaseResultStatus.PASSED;

/**
 * Potentially we can add - BeforeTestExecutionCallback, AfterTestExecutionCallback
 * TODO should this also do all the timings?
 * TODO WordifyExtensionContext and TestCaseNameFactory need to be injectable and or overridable
 */
public class ReportExtension implements
    BeforeAllCallback, BeforeEachCallback, AfterAllCallback, AfterEachCallback, TestWatcher, InvocationInterceptor {
    private static final TestResults testResults = new TestResults();
    private static final WordifyExtensionContext wordifyExtensionContext = new WordifyExtensionContext();
    private static final TestCaseNameFactory testCaseNameFactory = new TestCaseNameFactory();

    //private static final LocalDateTime dateTime = LocalDateTime.now();
    //private static final ReportWriter reportWriter = new ReportWriter(dateTime);
    private static final ReportWriter reportWriter = new ReportWriter();
    private static boolean isReporting = false;

    private static void startReporting() {
        if (!isReporting) {
            reportWriter.prepare();
        }
        isReporting = true;
    }

    private static void writeTestSuiteResults() {
        getTestResults().getTestSuiteResults().forEach((reportWriter::write));
    }

    public static void writeIndex() {
        if (isReporting) {
            reportWriter.write(getTestResults());
        }
    }

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        startReporting();
        testResults.startTestSuite(context);
    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        getTestSuiteResult(context).startTestCase(context);
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        getTestSuiteResult(context).completeTestSuite();
        writeTestSuiteResults();
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
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

    /** normal test without params */
    @Override
    public void interceptTestMethod(Invocation<Void> invocation, ReflectiveInvocationContext<Method> invocationContext, ExtensionContext extensionContext) throws Throwable {
        updateTestCaseResult(invocationContext, extensionContext);
        invocation.proceed();
    }

    /**
     * Using (at)TestFactory??? Not tested.
     * */
    @Override
    public <T> T interceptTestFactoryMethod(Invocation<T> invocation, ReflectiveInvocationContext<Method> invocationContext, ExtensionContext extensionContext) throws Throwable {
        updateTestCaseResult(invocationContext, extensionContext);
        return invocation.proceed();
    }

    /**
     * Pass in params:
     * (at)ParameterizedTest
     * (at)ValueSource(strings = { "value 1", "value 2", "value 3" })
     * void paramTest(String param) {
     *     passingAssertionWith(param);
     * }
     */
    @Override
    public void interceptTestTemplateMethod(Invocation<Void> invocation, ReflectiveInvocationContext<Method> invocationContext, ExtensionContext extensionContext) throws Throwable {
        updateTestCaseResult(invocationContext, extensionContext);
        invocation.proceed();
    }

    /**
     * Using (at)TestFactory??? Not tested.
     */
    @Override
    public void interceptDynamicTest(Invocation<Void> invocation, ExtensionContext extensionContext) throws Throwable {
        // no invocationContext???
        //updateTestCaseResult(invocationContext, extensionContext);
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
        getTestCaseResult(context).setStatus(PASSED);
    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        getTestSuiteResult(context).startTestCase(context).setStatus(DISABLED);
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        getTestCaseResult(context).setStatus(ABORTED).setCause(cause);
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        getTestCaseResult(context).setStatus(FAILED).setCause(cause);
    }

    public static TestResults getTestResults() {
        return testResults;
    }

    private TestSuiteResult getTestSuiteResult(ExtensionContext context) {
        return testResults.getTestResultsForClass(context);
    }

    private TestCaseResult getTestCaseResult(ExtensionContext context) {
        TestSuiteResult testSuiteResult = getTestSuiteResult(context);
        return testSuiteResult.getTestCaseResult(context);
    }

    private void updateTestCaseResult(ReflectiveInvocationContext<Method> invocationContext, ExtensionContext extensionContext) {
        TestCaseResult testCaseResult = getTestCaseResult(extensionContext);
        final List<Object> arguments = invocationContext.getArguments();
        final Method method = invocationContext.getExecutable();
        final Annotation[][] parameterAnnotations = method.getParameterAnnotations();

        wordifyExtensionContext.wordify(extensionContext, invocationContext.getArguments()).ifPresent(testCaseResult::setWordify);
        testCaseResult.setArgs(invocationContext.getArguments());
        testCaseResult.setName(testCaseNameFactory.createName(testCaseResult));
    }

    public static void reset() {
        testResults.reset();
    }
}
