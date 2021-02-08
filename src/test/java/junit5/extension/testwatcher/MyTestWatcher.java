package junit5.extension.testwatcher;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import wordify.WordifyClass;

import java.lang.reflect.Method;
import java.util.Optional;

public class MyTestWatcher implements TestWatcher {

    private static String result;

    public void testSuccessful(ExtensionContext context) {
        final Class<?> clazz = context.getRequiredTestClass();
        final Optional<Method> method = context.getTestMethod();
        method.ifPresent((m) -> wordify(clazz, m.getName()));
    }

    private void wordify(Class<?> clazz, String method) {
        final String wordify = new WordifyClass().wordify(clazz, method);
        result = wordify;
    }

    public static String getResult() {
        return result;
    }
}
