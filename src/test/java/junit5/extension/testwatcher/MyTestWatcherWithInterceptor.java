package junit5.extension.testwatcher;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.InvocationInterceptor;
import org.junit.jupiter.api.extension.ReflectiveInvocationContext;
import org.junit.jupiter.api.extension.TestWatcher;
import wordify.WordifyClass;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;

public class MyTestWatcherWithInterceptor implements TestWatcher, InvocationInterceptor {
    private static int testCount = 0;
    private static List<String> result = new ArrayList<>();
    private static List<List<Object>> params = new ArrayList<>();

    public <T> T interceptTestClassConstructor(Invocation<T> invocation,
                                                ReflectiveInvocationContext<Constructor<T>> invocationContext, ExtensionContext extensionContext)
        throws Throwable {
        return invocation.proceed();
    }

    public void interceptBeforeAllMethod(Invocation<Void> invocation,
                                          ReflectiveInvocationContext<Method> invocationContext, ExtensionContext extensionContext) throws Throwable {
        invocation.proceed();
    }

    public void interceptBeforeEachMethod(Invocation<Void> invocation,
                                           ReflectiveInvocationContext<Method> invocationContext, ExtensionContext extensionContext) throws Throwable {
        testCount++;
        invocation.proceed();
    }

    public void interceptTestMethod(Invocation<Void> invocation, ReflectiveInvocationContext<Method> invocationContext,
                                     ExtensionContext extensionContext) throws Throwable {
        invocation.proceed();
    }

    public <T> T interceptTestFactoryMethod(Invocation<T> invocation,
                                             ReflectiveInvocationContext<Method> invocationContext, ExtensionContext extensionContext) throws Throwable {
        return invocation.proceed();
    }

    public void interceptTestTemplateMethod(Invocation<Void> invocation,
                                             ReflectiveInvocationContext<Method> invocationContext, ExtensionContext extensionContext) throws Throwable {
        params.add(invocationContext.getArguments());
        invocation.proceed();
    }

    public void interceptDynamicTest(Invocation<Void> invocation, ExtensionContext extensionContext) throws Throwable {
        invocation.proceed();
    }

    public void interceptAfterEachMethod(Invocation<Void> invocation,
                                          ReflectiveInvocationContext<Method> invocationContext, ExtensionContext extensionContext) throws Throwable {
        invocation.proceed();
    }

    public void interceptAfterAllMethod(Invocation<Void> invocation,
                                         ReflectiveInvocationContext<Method> invocationContext, ExtensionContext extensionContext) throws Throwable {
        invocation.proceed();
    }


    @Override
    public void testSuccessful(ExtensionContext context) {
        final List<Object> parameters = params.isEmpty() ? emptyList() : params.get(params.size() - 1);
        Class<?> clazz = context.getRequiredTestClass();
        Optional<Method> method = context.getTestMethod();
        method.ifPresent((m) -> wordify(clazz, m, parameters));
    }

    private void wordify(Class<?> clazz, Method method, List<Object> parameters) {
        String wordify = new WordifyClass().wordify(clazz, method.getName(), parameters);
        result.add(wordify);
    }

    public static List<String> getResult() {
        return result;
    }

    public static List<List<Object>> getParams() {
        return params;
    }

    public static void clearResults() {
        result.clear();
        params.clear();
    }
}
