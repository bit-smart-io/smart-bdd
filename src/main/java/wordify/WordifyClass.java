package wordify;

import com.thoughtworks.qdox.model.JavaMethod;
import com.thoughtworks.qdox.model.JavaParameter;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

public class WordifyClass {
    private static final Logger logger = Logger.getLogger(WordifyClass.class.getName());

    public String wordify(Class<?> clazz, String methodName) {
        return wordify(clazz, methodName, emptyList());
    }

    public String wordify(Class<?> clazz, String methodName, List<Object> parameterValues) {
        try {
            JavaSourceWrapper sourceWrapper = new JavaSourceWrapper(clazz);
            List<JavaMethod> allMethods = sourceWrapper.getMethods();
            List<JavaMethod> matchedMethods = allMethods.stream().filter(m -> m.getName().contains(methodName)).collect(toList());
            JavaMethod matchedMethod = matchedMethods.get(0);
            String sourceCode = matchedMethod.getSourceCode();
            List<JavaParameter> parameters = sourceWrapper.getParams(matchedMethod.getName());
            sourceCode = updateSourceCode(sourceCode, parameters, parameterValues);
            String wordify = new WordifyString(sourceCode).wordify();
            return wordify;
        } catch (IOException e) {
            logger.log(Level.WARNING, "Could not load Java source", e);
            return "Could not load Java source: " + e;
        }
    }

    private String updateSourceCode(String sourceCode, List<JavaParameter> parameters, List<Object> parameterValues) {
        try {
            int count = 0;
            for (JavaParameter param : parameters) {
                sourceCode = sourceCode.replace(param.getName(), parameterValues.get(count).toString());
                count++;
            }
            return sourceCode;
        } catch (Exception e) {
            System.out.println("WordifyClass Error - parameters: " + parameters + ", parameterValues: " + parameterValues + ", sourceCode: " + sourceCode);
            return sourceCode;
        }
    }
}
