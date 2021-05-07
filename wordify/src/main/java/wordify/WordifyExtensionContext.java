package wordify;

import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.List;
import java.util.Optional;

public class WordifyExtensionContext {
    private WordifyClass wordify = new WordifyClass();

    public Optional<String> wordify(ExtensionContext context, List<Object> parameters) {
        return context.getTestMethod()
            .map(method -> wordify.wordify(context.getRequiredTestClass(), method.getName(), parameters));
    }
}
