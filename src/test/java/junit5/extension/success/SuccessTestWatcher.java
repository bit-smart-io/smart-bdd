package junit5.extension.success;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import wordify.WordifyExtensionContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SuccessTestWatcher implements TestWatcher {
    private static List<String> results = new ArrayList<>();
    private static WordifyExtensionContext wordifyExtensionContext = new WordifyExtensionContext();

    @Override
    public void testSuccessful(ExtensionContext context) {
        wordifyExtensionContext.wordify(context, Collections.emptyList()).ifPresent(result -> results.add(result));
    }

    public static List<String> getResults() {
        return results;
    }

    public static void clearResults() {
        results.clear();
    }
}
