package wordify;

import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.List;
import java.util.Optional;

public class WordifyExtensionContext {
    private WordifyClass wordify = new WordifyClass();

    public Optional<String> wordify(ExtensionContext context, List<Object> parameters) {
        Class<?> clazz = context.getRequiredTestClass();
        return context.getTestMethod()
            .map(m -> wordify.wordify(clazz, m.getName(), parameters));
    }
}
