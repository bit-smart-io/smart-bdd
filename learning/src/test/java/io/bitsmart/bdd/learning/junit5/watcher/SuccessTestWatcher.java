package io.bitsmart.bdd.learning.junit5.watcher;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.util.ArrayList;
import java.util.List;

public class SuccessTestWatcher implements TestWatcher {
    private static List<String> results = new ArrayList<>();

    @Override
    public void testSuccessful(ExtensionContext context) {
        results.add("success");
    }

    public static List<String> getResults() {
        return results;
    }

    public static void clearResults() {
        results.clear();
    }
}
