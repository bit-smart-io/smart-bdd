package junit5.debugextension.utils.debugcapture;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.InvocationInterceptor;
import org.junit.jupiter.api.extension.ReflectiveInvocationContext;
import junit5.debugextension.utils.debugcapture.methods.BaseMethod;
import junit5.debugextension.utils.debugcapture.methods.InterceptBaseMethod;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class CaptureTestClass {
    private final List<String> methodNames = new ArrayList<>();
    private final ConcurrentHashMap<ExtensionContext, CaptureTestMethod> contextToCapturedTestMethod = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, List<ExtensionContext>> methodNameToContext = new ConcurrentHashMap<>();
    private final CaptureTestMethod capturedMethodsForClass = new CaptureTestMethod();

    public CaptureTestMethod newCaptureTestMethod(ExtensionContext context) {
        CaptureTestMethod captureTestMethod = new CaptureTestMethod();
        String methodName = getMethodName(context);
        methodNames.add(methodName);

        if(methodNameToContext.containsKey(methodName)){
            methodNameToContext.get(methodName).add(context);
        } else {
            List<ExtensionContext> contexts = new ArrayList<>();
            contexts.add(context);
            methodNameToContext.put(methodName, contexts);
        }

        contextToCapturedTestMethod.put(context, captureTestMethod);
        return captureTestMethod;
    }

    public CaptureTestMethod getCaptureTestMethod(ExtensionContext context) {
        return contextToCapturedTestMethod.get(context);
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

    public ConcurrentHashMap<String, List<ExtensionContext>> getMethodNameToContext() {
        return methodNameToContext;
    }

    public CaptureTestMethod getCapturedTestMethod(String testMethodName) {
        ExtensionContext extensionContext = methodNameToContext.get(testMethodName).get(0);
        return contextToCapturedTestMethod.get(extensionContext);
    }

    public List<CaptureTestMethod> getCapturedTestMethods(String testMethodName) {
        return methodNameToContext.get(testMethodName).stream().map(context -> contextToCapturedTestMethod.get(context)).collect(Collectors.toList());
    }

    public List<String> getMethodNames() {
        return methodNames;
    }

    public ConcurrentHashMap<ExtensionContext, CaptureTestMethod> getContextToCapturedTestMethod() {
        return contextToCapturedTestMethod;
    }

    public CaptureTestMethod getCapturedMethodsForClass() {
        return capturedMethodsForClass;
    }
}
