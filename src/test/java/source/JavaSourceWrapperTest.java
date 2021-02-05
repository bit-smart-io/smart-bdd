package source;

import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaMethod;
import com.thoughtworks.qdox.model.JavaSource;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.CodeSource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.System.getProperty;
import static org.assertj.core.api.Assertions.assertThat;

class JavaSourceWrapperTest {
    private static final String CLASS_NAME = "JavaSourceWrapperTest";
    private static final String QUALIFIED_CLASS_NAME = "source.JavaSourceWrapperTest";
    private static final String QUALIFIED_CLASS_PATH = "source/JavaSourceWrapperTest.java";

    @Test
    void loadJavaSource() throws IOException {
        final JavaSourceWrapper javaSourceWrapper = new JavaSourceWrapper(this.getClass());
        assertThat(javaSourceWrapper.getMethods()).isNotNull();
    }

    /**
     * https://www.baeldung.com/java-list-directory-files
     **/
    @Test
    void listDirectories() throws IOException {
        final Set<String> dirs = listDirectories(workingDirectory().getPath());
        assertThat(dirs).isNotNull();
    }

    /**
     * src/test/java/
     */
    @Test
    void getSrcDir() {
        final File file = new File(workingDirectory() + File.separator
            + "src" + File.separator
            + "test" + File.separator
            + "java" + File.separator);
        assertThat(file).exists();

        final Class<? extends JavaSourceWrapperTest> clazz = this.getClass();
        final String relateSource = clazz.getName().replace('.', File.separatorChar) + ".java";

        final File javaSourceFile = new File(file.getAbsolutePath() + File.separator + relateSource);
        assertThat(javaSourceFile).exists();
    }

    @Test
    void sourceForClass() throws IOException {
        File javaSourceFile = sourceFor(this.getClass());
        assertThat(javaSourceFile).exists();

        JavaProjectBuilder builder = new JavaProjectBuilder();
        final JavaSource javaSource = builder.addSource(javaSourceFile);
        assertThat(javaSource).isNotNull();

        final JavaClass javaClass = builder.getClassByName(this.getClass().getName());
        assertThat(javaClass).isNotNull();

        final List<JavaMethod> methods = javaClass.getMethods();
        assertThat(methods).isNotNull();

        final List<JavaMethod> matchedMethods = methods.stream().filter(method -> method.getName().endsWith("sourceForClass")).collect(Collectors.toList());
        assertThat(matchedMethods).isNotEmpty();

        final JavaMethod method = matchedMethods.get(0);
        assertThat(method).isNotNull();
    }

    private File sourceFor(Class<?> clazz) {
        final File file = new File(workingDirectory() + File.separator
            + "src" + File.separator
            + "test" + File.separator
            + "java" + File.separator);
        final String relateSource = clazz.getName().replace('.', File.separatorChar) + ".java";

        return new File(file.getAbsolutePath() + File.separator + relateSource);
    }

    @Test
    void learningTestUserDir() {
        final File file = workingDirectory();
        assertThat(file).exists();
        assertThat(file).isDirectory();
        assertThat(file).isAbsolute();
    }

    @Test
    void learningTestGetJavaSourceFromClassPath() {
        final Class<? extends JavaSourceWrapperTest> clazz = this.getClass();
        final String fileLocation = clazz.getName().replace('.', File.separatorChar) + ".java";
        assertThat(fileLocation).isEqualTo(QUALIFIED_CLASS_PATH);

        final URL resource = clazz.getClassLoader().getResource(fileLocation);
        assertThat(resource).isNull();
    }

    @Test
    void learningTestForClassMethods() {
        final Class<? extends JavaSourceWrapperTest> clazz = this.getClass();
        assertThat(clazz.getSimpleName()).isEqualTo(CLASS_NAME);
        assertThat(clazz.getName()).isEqualTo(QUALIFIED_CLASS_NAME);
        assertThat(clazz.getTypeName()).isEqualTo(QUALIFIED_CLASS_NAME);
        assertThat(clazz.getCanonicalName()).isEqualTo(QUALIFIED_CLASS_NAME);

        final CodeSource codeSource = clazz.getProtectionDomain().getCodeSource();
        assertThat(codeSource.getLocation().getPath()).endsWith("/build/classes/java/test/");
    }

    private Set<String> listDirectories(String dir) throws IOException {
        Set<String> directories = new HashSet<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(dir))) {
            for (Path path : stream) {
                if (Files.isDirectory(path)) {
                    directories.add(path.getFileName().toString());
                }
            }
        }
        return directories;
    }

    public static File workingDirectory() {
        return new File(getProperty("user.dir"));
    }
}