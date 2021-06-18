package junit5.results.model;

import java.util.Objects;

public class TestSuiteClass {
    private final String fullyQualifiedName;
    private final String className;
    private final String packageName;

    private TestSuiteClass(String fullyQualifiedName, String className, String packageName) {
        this.fullyQualifiedName = fullyQualifiedName;
        this.className = className;
        this.packageName = packageName;
    }

    public static TestSuiteClass testSuiteClass(Class<?> clazz) {
        return new TestSuiteClass(clazz.getName(), clazz.getSimpleName(), clazz.getPackage().getName());
    }

    public String getFullyQualifiedName() {
        return fullyQualifiedName;
    }

    public String getClassName() {
        return className;
    }

    public String getPackageName() {
        return packageName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TestSuiteClass)) return false;
        TestSuiteClass that = (TestSuiteClass) o;
        return Objects.equals(fullyQualifiedName, that.fullyQualifiedName) && Objects.equals(className, that.className) && Objects.equals(packageName, that.packageName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullyQualifiedName, className, packageName);
    }

    @Override
    public String toString() {
        return "TestSuiteResultsId{" +
            "name='" + fullyQualifiedName + '\'' +
            ", className='" + className + '\'' +
            ", packageName='" + packageName + '\'' +
            '}';
    }
}
