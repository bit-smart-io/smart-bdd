package results.junit.testcapture;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.InvocationInterceptor;
import org.junit.jupiter.api.extension.ReflectiveInvocationContext;
import results.junit.testcapture.methods.BaseMethod;
import results.junit.testcapture.methods.InterceptBaseMethod;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CaptureTestMethod {
    private Map<String, BaseMethod> methodsCaptured = new HashMap<>();
    private List<String> methodNamesCaptured = new ArrayList<>();

    public List<String> getMethodNames() {
        return methodNamesCaptured;
    }

    public Map<String, BaseMethod> getMethods() {
        return methodsCaptured;
    }

    public void add(BaseMethod method) {
        methodNamesCaptured.add(method.getName());
        put(method.getName(), method);
    }

    public void add(
        String name,
        InvocationInterceptor.Invocation<Void> invocation,
        ReflectiveInvocationContext<Method> invocationContext,
        ExtensionContext extensionContext) {
        add(new InterceptBaseMethod(name, invocation, invocationContext, extensionContext));
    }

//    private static int count = 0;
    private void put(String name, BaseMethod method) {
        methodsCaptured.put(name, method);
//        System.out.println("" + count + ": " + getMethodNames());
//        count++;
    }

    @Override
    public String toString() {
        return "ExtensionCalls{" +
            "extensionMethods=" + methodsCaptured +
            '}';
    }
}
