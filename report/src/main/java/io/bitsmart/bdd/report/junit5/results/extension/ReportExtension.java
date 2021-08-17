package io.bitsmart.bdd.report.junit5.results.extension;

import io.bitsmart.bdd.report.junit5.results.model.TestCaseNameFactory;
import io.bitsmart.bdd.report.junit5.results.model.TestResults;
import io.bitsmart.bdd.report.report.writers.ReportWriter;
import io.bitsmart.wordify.WordifyExtensionContext;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.InvocationInterceptor;
import org.junit.jupiter.api.extension.ReflectiveInvocationContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Optional;

/**
 * Potentially we can add - BeforeTestExecutionCallback, AfterTestExecutionCallback
 * TODO should this also do all the timings?
 * TODO WordifyExtensionContext and TestCaseNameFactory need to be injectable and or overridable
 */
public class ReportExtension implements
    BeforeAllCallback, BeforeEachCallback, AfterAllCallback, AfterEachCallback, TestWatcher, InvocationInterceptor {

    private static final TestContext testContext = new TestContext(
        new TestResults(),
        new WordifyExtensionContext(),
        new TestCaseNameFactory(),
        new ReportWriter()
    );

    public static TestContext getTestContext() {
        return testContext;
    }

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        testContext.beforeAll(context);
    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        testContext.beforeEach(context);
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        testContext.afterAll(context);
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
        testContext.interceptTestMethod(invocation, invocationContext, extensionContext);
    }

    /**
     * Using (at)TestFactory??? Not tested.
     * */
    @Override
    public <T> T interceptTestFactoryMethod(Invocation<T> invocation, ReflectiveInvocationContext<Method> invocationContext, ExtensionContext extensionContext) throws Throwable {
        return testContext.interceptTestFactoryMethod(invocation, invocationContext, extensionContext);
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
        testContext.interceptTestTemplateMethod(invocation, invocationContext, extensionContext);
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
        testContext.testSuccessful(context);
    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        testContext.testDisabled(context, reason);
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        testContext.testAborted(context, cause);
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        testContext.testFailed(context, cause);
    }
}
