package io.bitsmart.wordify;

import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaMethod;
import com.thoughtworks.qdox.model.JavaParameter;
import com.thoughtworks.qdox.model.JavaSource;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.lang.System.getProperty;

public class JavaSourceWrapper {
    private final JavaProjectBuilder builder = new JavaProjectBuilder();
    private final File javaSourceFile;
    private final JavaSource javaSource;
    private final JavaClass javaClass;
    private final String DEFAULT_SRC_PATH = "src" + File.separatorChar + "test" + File.separatorChar + "java" + File.separatorChar;

    public JavaSourceWrapper(Class clazz) throws IOException {
        javaSourceFile = sourceFor(clazz);
        javaSource = builder.addSource(javaSourceFile);
        javaClass = builder.getClassByName(clazz.getName());
    }

    public List<JavaMethod> getMethods() {
        return javaClass.getMethods();
    }

    /**
     * TODO you'd have to match the number of params
     * TODO prototyping code not production ready
     * @param methodName
     * @return
     */
    public List<JavaParameter> getParams(String methodName) {
        Optional<JavaMethod> javaMethod = javaClass.getMethods().stream().filter(m -> m.getName().equals(methodName)).findFirst();
        if (javaMethod.isPresent()) {
            return javaMethod.get().getParameters();
        }
        return Collections.emptyList();
    }

    private static File workingDirectory() {
        return new File(getProperty("user.dir"));
    }

    private File sourceFor(Class<?> clazz) {
        String relateSource = clazz.getName().replace('.', File.separatorChar) + ".java";
        return new File(workingDirectory() + File.separator + DEFAULT_SRC_PATH + relateSource);
    }
}
