package wordify;

import com.thoughtworks.qdox.model.JavaMethod;
import source.JavaSourceWrapper;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.stream.Collectors.toList;

public class WordifyClass {
    private static final Logger logger = Logger.getLogger(WordifyClass.class.getName());

    public String wordify(Class<?> clazz, String methodName) {
        try {
            final JavaSourceWrapper javaSourceWrapper = new JavaSourceWrapper(clazz);
            final List<JavaMethod> methods = javaSourceWrapper.getMethods();
            final List<JavaMethod> matchedMethods = methods.stream().filter(m -> m.getName().contains(methodName)).collect(toList());
            final String sourceCode = matchedMethods.get(0).getSourceCode();
            String wordify = new WordifyString(sourceCode).wordify();
            return wordify;
        } catch (IOException e) {
            logger.log(Level.WARNING, "Could not load Java source", e);
            return "Could not load Java source: " + e;
        }
    }
}
