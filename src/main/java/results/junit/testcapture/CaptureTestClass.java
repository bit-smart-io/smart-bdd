package results.junit.testcapture;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.InvocationInterceptor;
import org.junit.jupiter.api.extension.ReflectiveInvocationContext;
import results.junit.testcapture.methods.BaseMethod;
import results.junit.testcapture.methods.InterceptBaseMethod;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

public class CaptureTestClass {
    // TODO naming - capturing LifeCycleMethodsForClass
    //               capturing callbacks
    //               capturing events
    private ConcurrentHashMap<String, CaptureTestMethod> capturedMethodsForTestMethods = new ConcurrentHashMap();
    private CaptureTestMethod capturedMethodsForClass = new CaptureTestMethod();

    public CaptureTestMethod startNewTestMethod(ExtensionContext context) {
        CaptureTestMethod captureTestMethod = new CaptureTestMethod();
        capturedMethodsForTestMethods.put(getMethodName(context), captureTestMethod);
        return captureTestMethod;
    }

    public CaptureTestMethod getTestMethod(ExtensionContext context) {
        return capturedMethodsForTestMethods.get(getMethodName(context));
    }

    public void add(BaseMethod baseMethod) {
        capturedMethodsForClass.add(baseMethod);
    }

    public void add(
        String name,
        InvocationInterceptor.Invocation<Void> invocation,
        ReflectiveInvocationContext<Method> invocationContext,
        ExtensionContext extensionContext) {
        add(new InterceptBaseMethod(name, invocation, invocationContext, extensionContext));
    }

    private String getMethodName(ExtensionContext context) {
        return context.getTestMethod().map(m -> m.getName()).orElse("could-not-get-method-name");
    }

    public ConcurrentHashMap<String, CaptureTestMethod> getCapturedMethodsForTestMethods() {
        return capturedMethodsForTestMethods;
    }

    public CaptureTestMethod getCapturedMethodsForClass() {
        return capturedMethodsForClass;
    }
}
