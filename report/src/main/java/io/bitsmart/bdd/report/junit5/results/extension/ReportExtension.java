package io.bitsmart.bdd.report.junit5.results.extension;

import io.bitsmart.bdd.report.junit5.results.model.TestSuiteResult;
import io.bitsmart.bdd.report.junit5.results.model.Results;
import io.bitsmart.bdd.report.junit5.results.model.TestCaseResult;
import io.bitsmart.bdd.report.junit5.results.model.TestCaseNameFactory;
import io.bitsmart.bdd.report.report.ReportWriter;
import io.bitsmart.bdd.report.report.adapter.ReportFactory;
import io.bitsmart.bdd.report.report.model.Report;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.InvocationInterceptor;
import org.junit.jupiter.api.extension.ReflectiveInvocationContext;
import org.junit.jupiter.api.extension.TestWatcher;
import io.bitsmart.wordify.WordifyExtensionContext;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
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
    private static final Results results = new Results();
    private static final WordifyExtensionContext wordifyExtensionContext = new WordifyExtensionContext();
    private static final TestCaseNameFactory testCaseNameFactory = new TestCaseNameFactory();

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        results.startTestSuite(context);
    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        getTestSuiteResult(context).startTestCase(context);
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        getTestSuiteResult(context).completeTestSuite();

        // if has test TestExecutionListener
        //   then TestExecutionListener#testPlanExecutionFinished can write the report
        // else
        //   can write the report here

        // depending on the strategy write the report and or http post the results/report

        Report report = ReportFactory.create(getResults());
        ReportWriter reportWriter = new ReportWriter();
        reportWriter.write(report);

        //where is the report??
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

    public static Results getResults() {
        return results;
    }

    private TestSuiteResult getTestSuiteResult(ExtensionContext context) {
        return results.getTestResultsForClass(context);
    }

    private TestCaseResult getTestCaseResult(ExtensionContext context) {
        TestSuiteResult testSuiteResult = getTestSuiteResult(context);
        return testSuiteResult.getTestCaseResult(context);
    }

    private void updateTestCaseResult(ReflectiveInvocationContext<Method> invocationContext, ExtensionContext extensionContext) {
        TestCaseResult testCaseResult = getTestCaseResult(extensionContext);
        wordifyExtensionContext.wordify(extensionContext, invocationContext.getArguments()).ifPresent(testCaseResult::setWordify);
        testCaseResult.setArgs(invocationContext.getArguments());
        testCaseResult.setName(testCaseNameFactory.createName(testCaseResult));
    }

    public static void reset() {
        results.reset();
    }
}
