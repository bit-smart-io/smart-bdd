package junit5.extension.testwatcher;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.InvocationInterceptor;
import org.junit.jupiter.api.extension.ReflectiveInvocationContext;
import org.junit.jupiter.api.extension.TestWatcher;
import results.TestResultCollector;
import wordify.WordifyExtensionContext;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class TestResultsWatcher implements BeforeAllCallback, TestWatcher, InvocationInterceptor {
    private static TestResultCollector testResultCollector = new TestResultCollector();
    private static WordifyExtensionContext wordifyExtensionContext = new WordifyExtensionContext();

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        // is this called before all on each test class
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
        invocation.proceed();
    }

    public <T> T interceptTestFactoryMethod(Invocation<T> invocation, ReflectiveInvocationContext<Method> invocationContext, ExtensionContext extensionContext) throws Throwable {
        return invocation.proceed();
    }

    public void interceptTestTemplateMethod(Invocation<Void> invocation, ReflectiveInvocationContext<Method> invocationContext, ExtensionContext extensionContext) throws Throwable {
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


    //    @Override
//    public void testSuccessful(ExtensionContext context) {
//        List<Object> parameters = params.isEmpty() ? emptyList() : params.get(params.size() - 1);
//        Class<?> clazz = context.getRequiredTestClass();
//        Optional<Method> method = context.getTestMethod();
//        method.ifPresent((m) -> wordify(clazz, m, parameters));
//    }
//
//    private void wordify(Class<?> clazz, Method method, List<Object> parameters) {
//        String wordify = this.wordify.wordify(clazz, method.getName(), parameters);
//        result.add(wordify);
//    }
//
//    public static WordifyClass getWordify() {
//        return wordify;
//    }
//
//    public static void setWordify(WordifyClass wordify) {
//        TestResultsWatcher.wordify = wordify;
//    }
//
//    public static List<String> getResult() {
//        return result;
//    }
//
//    public static List<List<Object>> getParams() {
//        return params;
//    }
//
//    public static void clearResults() {
//        result.clear();
//        params.clear();
//    }
//
//    public static TestResults getTestResults() {
//        return testResults;
//    }
//
//    static class TestResults {
//        private int testsPassed = 0;
//
//        public int getTestsPassed() {
//            return testsPassed;
//        }
//
//        public void incrementTestsPassed() {
//            testsPassed++;
//        }
//    }

}
