package junit5.extension.testwatcher.debug;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.InvocationInterceptor;
import org.junit.jupiter.api.extension.ReflectiveInvocationContext;
import org.junit.jupiter.api.extension.TestWatcher;
import results.junit.testcapture.CaptureTestClass;
import results.junit.testcapture.CaptureTestClasses;
import results.junit.testcapture.CaptureTestMethod;
import results.junit.testcapture.methods.Callback;
import results.junit.testcapture.methods.DynamicTest;
import results.junit.testcapture.methods.TestClassConstructor;
import results.junit.testcapture.methods.InterceptTestFactoryBaseMethod;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Used for learning
 */
public class DebugExtension implements
    BeforeAllCallback, BeforeEachCallback, AfterAllCallback, AfterEachCallback, TestWatcher, InvocationInterceptor {
    private static final List<String> allMethodNames = new ArrayList<>();
    private static final CaptureTestClasses captureTestClasses = new CaptureTestClasses();

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        CaptureTestClass captureTestClass = captureTestClasses.newCaptureTestClass(context);
        captureTestClass.add(new Callback("beforeAll", context));
        allMethodNames.add("beforeAll");
    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        CaptureTestClass captureTestClass = captureTestClasses.getCaptureTestClass(context);
        CaptureTestMethod captureTestMethod = captureTestClass.newCaptureTestMethod(context);
        captureTestMethod.add(new Callback("beforeEach", context));

        allMethodNames.add("beforeEach");
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        CaptureTestClass captureTestClass = captureTestClasses.getCaptureTestClass(context);
        captureTestClass.add(new Callback("afterAll", context));
        allMethodNames.add("afterAll");
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        CaptureTestMethod captureTestMethod = getCaptureTestMethod(context);
        captureTestMethod.add(new Callback("afterEach", context));
        allMethodNames.add("afterEach");
    }

    public <T> T interceptTestClassConstructor(Invocation<T> invocation, ReflectiveInvocationContext<Constructor<T>> invocationContext, ExtensionContext extensionContext) throws Throwable {
        CaptureTestClass captureTestClass = captureTestClasses.getCaptureTestClass(extensionContext);
        captureTestClass.add(new TestClassConstructor<>("interceptTestClassConstructor", invocation, invocationContext, extensionContext));
        allMethodNames.add("interceptTestClassConstructor");
        return invocation.proceed();
    }

    public void interceptBeforeAllMethod(Invocation<Void> invocation, ReflectiveInvocationContext<Method> invocationContext, ExtensionContext extensionContext) throws Throwable {
        CaptureTestClass captureTestClass = captureTestClasses.getCaptureTestClass(extensionContext);
        captureTestClass.add("interceptBeforeAllMethod", invocation, invocationContext, extensionContext);
        allMethodNames.add("interceptBeforeAllMethod");
        invocation.proceed();
    }

    public void interceptBeforeEachMethod(Invocation<Void> invocation, ReflectiveInvocationContext<Method> invocationContext, ExtensionContext extensionContext) throws Throwable {
        CaptureTestMethod captureTestMethod = getCaptureTestMethod(extensionContext);
        captureTestMethod.add("interceptBeforeEachMethod", invocation, invocationContext, extensionContext);
        allMethodNames.add("interceptBeforeEachMethod");
        invocation.proceed();
    }

    public void interceptTestMethod(Invocation<Void> invocation, ReflectiveInvocationContext<Method> invocationContext, ExtensionContext extensionContext) throws Throwable {
        CaptureTestMethod captureTestMethod = getCaptureTestMethod(extensionContext);
        captureTestMethod.add("interceptTestMethod", invocation, invocationContext, extensionContext);
        allMethodNames.add("interceptTestMethod");
        invocation.proceed();
    }

    public <T> T interceptTestFactoryMethod(Invocation<T> invocation, ReflectiveInvocationContext<Method> invocationContext, ExtensionContext extensionContext) throws Throwable {
        CaptureTestMethod captureTestMethod = getCaptureTestMethod(extensionContext);
        captureTestMethod.add(new InterceptTestFactoryBaseMethod("interceptTestFactoryMethod", invocation, invocationContext, extensionContext));
        allMethodNames.add("interceptTestFactoryMethod");
        return invocation.proceed();
    }

    public void interceptTestTemplateMethod(Invocation<Void> invocation, ReflectiveInvocationContext<Method> invocationContext, ExtensionContext extensionContext) throws Throwable {
        CaptureTestMethod captureTestMethod = getCaptureTestMethod(extensionContext);
        captureTestMethod.add("interceptTestTemplateMethod", invocation, invocationContext, extensionContext);
        allMethodNames.add("name");
        invocation.proceed();
    }

    public void interceptDynamicTest(Invocation<Void> invocation, ExtensionContext extensionContext) throws Throwable {
        CaptureTestMethod captureTestMethod = getCaptureTestMethod(extensionContext);
        captureTestMethod.add(new DynamicTest("interceptDynamicTest", invocation, extensionContext));
        allMethodNames.add("interceptDynamicTest");
        invocation.proceed();
    }

    public void interceptAfterEachMethod(Invocation<Void> invocation, ReflectiveInvocationContext<Method> invocationContext, ExtensionContext extensionContext) throws Throwable {
        CaptureTestMethod captureTestMethod = getCaptureTestMethod(extensionContext);
        captureTestMethod.add("interceptAfterEachMethod", invocation, invocationContext, extensionContext);
        allMethodNames.add("interceptAfterEachMethod");
        invocation.proceed();
    }

    public void interceptAfterAllMethod(Invocation<Void> invocation, ReflectiveInvocationContext<Method> invocationContext, ExtensionContext extensionContext) throws Throwable {
        CaptureTestClass captureTestClass = captureTestClasses.getCaptureTestClass(extensionContext);
        captureTestClass.add("interceptAfterAllMethod", invocation, invocationContext, extensionContext);
        allMethodNames.add("interceptAfterAllMethod");
        invocation.proceed();
    }

    public static List<String> getAllMethodNames() {
        return allMethodNames;
    }

    public static CaptureTestClasses getCapturedTestClasses() {
        return captureTestClasses;
    }

    private CaptureTestMethod getCaptureTestMethod(ExtensionContext context) {
        CaptureTestClass captureTestClass = captureTestClasses.getCaptureTestClass(context);
        CaptureTestMethod captureTestMethod = captureTestClass.getCaptureTestMethod(context);
//        System.out.println("captureTestMethod:" + captureTestMethod);
        return captureTestMethod;
    }
}