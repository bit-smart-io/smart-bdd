package report;

import junit5.results.ClassResults;
import junit5.results.AllResults;
import junit5.results.TestResult;
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

import static junit5.results.TestResult.Status.PASSED;


public class ResultsExtension implements
    BeforeAllCallback, BeforeEachCallback, AfterAllCallback, AfterEachCallback, TestWatcher, InvocationInterceptor {
    private static final AllResults allResults = new AllResults();
    private static final WordifyExtensionContext wordifyExtensionContext = new WordifyExtensionContext();

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        ClassResults classResults = allResults.newResultsForClass(context);
    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        ClassResults classResults = allResults.getTestResultsForClass(context);
        TestResult testResult = classResults.newResultsForTest(context);
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
        TestResult testResult = getTestResultsForTest(context);
        testResult.setStatus(PASSED);
    }

    public static AllResults getTestResultsForClasses() {
        return allResults;
    }

    private ClassResults getTestResultsForClass(ExtensionContext context) {
        return allResults.getTestResultsForClass(context);
    }

    private TestResult getTestResultsForTest(ExtensionContext context) {
        ClassResults classResults = allResults.getTestResultsForClass(context);
        return classResults.getResultsForTest(context);
    }

    private void wordify(ReflectiveInvocationContext<Method> invocationContext, ExtensionContext extensionContext, String methodName) {
        TestResult testResult = getTestResultsForTest(extensionContext);
        wordifyExtensionContext.wordify(extensionContext, invocationContext.getArguments()).ifPresent(testResult::setWordify);
//        System.out.println(methodName + ", method: " + extensionContext.getTestMethod() + ", wordify: " + wordify);
    }

    public static void reset() {
        allResults.reset();
    }
}
