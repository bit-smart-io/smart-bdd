package junit5.debugextension.utils.debugcapture;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.InvocationInterceptor;
import org.junit.jupiter.api.extension.ReflectiveInvocationContext;
import junit5.debugextension.utils.debugcapture.methods.BaseMethod;
import junit5.debugextension.utils.debugcapture.methods.InterceptBaseMethod;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CaptureTestMethod {
    private final Map<String, BaseMethod> capturedMethods = new HashMap<>();
    private final List<String> capturedMethodsNames = new ArrayList<>();

    public List<String> getCapturedMethodNames() {
        return capturedMethodsNames;
    }

    public Map<String, BaseMethod> getCapturedMethods() {
        return capturedMethods;
    }

    public void add(BaseMethod method) {
        capturedMethodsNames.add(method.getName());
        put(method.getName(), method);
    }

    public void add(
        String name,
        InvocationInterceptor.Invocation<Void> invocation,
        ReflectiveInvocationContext<Method> invocationContext,
        ExtensionContext extensionContext) {
        add(new InterceptBaseMethod(name, invocation, invocationContext, extensionContext));
    }

    private void put(String name, BaseMethod method) {
        capturedMethods.put(name, method);
    }

    @Override
    public String toString() {
        return "ExtensionCalls{" +
            "extensionMethods=" + capturedMethods +
            '}';
    }
}