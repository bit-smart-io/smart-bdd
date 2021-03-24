package junit5.extension.results;

import junit5.results.ResultsForClass;
import junit5.results.ResultsForClasses;
import junit5.results.ResultsForTest;
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

import static junit5.results.ResultsForTest.Status.PASSED;


public class ResultsExtension implements
    BeforeAllCallback, BeforeEachCallback, AfterAllCallback, AfterEachCallback, TestWatcher, InvocationInterceptor {
    private static ResultsForClasses resultsForClasses = new ResultsForClasses();
    private static WordifyExtensionContext wordifyExtensionContext = new WordifyExtensionContext();

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        ResultsForClass resultsForClass = resultsForClasses.newResultsForClass(context);
    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        ResultsForClass resultsForClass = resultsForClasses.getTestResultsForClass(context);
        ResultsForTest resultsForTest = resultsForClass.newResultsForTest(context);
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        //ResultsForClass captureTestClass = testResultCollector.afterAll(context);
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        //ResultsForClass captureTestClass = testResultCollector.afterEach(context);
    }

    public <T> T interceptTestClassConstructor(Invocation<T> invocation, ReflectiveInvocationContext<Constructor<T>> invocationContext, ExtensionContext extensionContext) throws Throwable {
        return invocation.proceed();
    }

    public void interceptBeforeAllMethod(Invocation<Void> invocation, ReflectiveInvocationContext<Method> invocationContext, ExtensionContext extensionContext) throws Throwable {
        invocation.proceed();
    }

    public void interceptBeforeEachMethod(Invocation<Void> invocation, ReflectiveInvocationContext<Method> invocationContext, ExtensionContext extensionContext) throws Throwable {
        invocation.proceed();
    }

    public void interceptTestMethod(Invocation<Void> invocation, ReflectiveInvocationContext<Method> invocationContext, ExtensionContext extensionContext) throws Throwable {
        wordify(invocationContext, extensionContext, "interceptTestMethod");
        invocation.proceed();
    }

    public <T> T interceptTestFactoryMethod(Invocation<T> invocation, ReflectiveInvocationContext<Method> invocationContext, ExtensionContext extensionContext) throws Throwable {
        wordify(invocationContext, extensionContext, "interceptTestFactoryMethod");
        return invocation.proceed();
    }

    public void interceptTestTemplateMethod(Invocation<Void> invocation, ReflectiveInvocationContext<Method> invocationContext, ExtensionContext extensionContext) throws Throwable {
        wordify(invocationContext, extensionContext, "interceptTestTemplateMethod");
        invocation.proceed();
    }

    public void interceptDynamicTest(Invocation<Void> invocation, ExtensionContext extensionContext) throws Throwable {
        invocation.proceed();
    }

    public void interceptAfterEachMethod(Invocation<Void> invocation, ReflectiveInvocationContext<Method> invocationContext, ExtensionContext extensionContext) throws Throwable {
        invocation.proceed();
    }

    public void interceptAfterAllMethod(Invocation<Void> invocation, ReflectiveInvocationContext<Method> invocationContext, ExtensionContext extensionContext) throws Throwable {
        invocation.proceed();
    }

    public void testSuccessful(ExtensionContext context) {
        ResultsForTest resultsForTest = getTestResultsForTest(context);
        resultsForTest.setStatus(PASSED);
    }

    public static ResultsForClasses getTestResultsForClasses() {
        return resultsForClasses;
    }

    private ResultsForClass getTestResultsForClass(ExtensionContext context) {
        return resultsForClasses.getTestResultsForClass(context);
    }

    private ResultsForTest getTestResultsForTest(ExtensionContext context) {
        ResultsForClass resultsForClass = resultsForClasses.getTestResultsForClass(context);
        return resultsForClass.getResultsForTest(context);
    }

    private void wordify(ReflectiveInvocationContext<Method> invocationContext, ExtensionContext extensionContext, String methodName) {
        ResultsForTest resultsForTest = getTestResultsForTest(extensionContext);
        wordifyExtensionContext.wordify(extensionContext, invocationContext.getArguments()).ifPresent(resultsForTest::setWordify);
//        System.out.println(methodName + ", method: " + extensionContext.getTestMethod() + ", wordify: " + wordify);
    }

    public static void reset() {
        resultsForClasses = new ResultsForClasses();
    }
}
