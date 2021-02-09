package junit5.extension.testwatcher;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import wordify.WordifyClass;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MyTestWatcher implements TestWatcher {
    private static List<String> result = new ArrayList<>();

    @Override
    public void testSuccessful(ExtensionContext context) {
        Class<?> clazz = context.getRequiredTestClass();
        Optional<Method> method = context.getTestMethod();
        method.ifPresent((m) -> wordify(clazz, m));
    }

    private void wordify(Class<?> clazz, Method method) {
        String wordify = new WordifyClass().wordify(clazz, method.getName());
        result.add(wordify);
    }

    public static List<String> getResult() {
        return result;
    }

    public static void clearResults() {
        result.clear();
    }
}
