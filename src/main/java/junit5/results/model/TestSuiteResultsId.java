package junit5.results.model;

import java.util.Objects;

public class TestSuiteResultsId {
    private final String name;
    private final String className;
    private final String packageName;

    public TestSuiteResultsId(Class<?> clazz) {
        this.name = clazz.getName();
        this.className =  clazz.getSimpleName();
        this.packageName = clazz.getPackage().getName();
    }

    public String getName() {
        return name;
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
        if (!(o instanceof TestSuiteResultsId)) return false;
        TestSuiteResultsId that = (TestSuiteResultsId) o;
        return Objects.equals(name, that.name) && Objects.equals(className, that.className) && Objects.equals(packageName, that.packageName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, className, packageName);
    }

    @Override
    public String toString() {
        return "TestSuiteResultsId{" +
            "name='" + name + '\'' +
            ", className='" + className + '\'' +
            ", packageName='" + packageName + '\'' +
            '}';
    }
}
