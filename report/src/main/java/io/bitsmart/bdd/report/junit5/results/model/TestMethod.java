package io.bitsmart.bdd.report.junit5.results.model;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * Fields for the test suite method that are available at compile time: name, params, tags etc...
 *
 * For runtime time information please see TestSuiteMethodInvocation
 */
public class TestMethod {

    private final String name;

    public TestMethod(String name) {
        this.name = name;
    }

    public static TestMethod testMethod(Method method) {
        if (method == null) {
            new TestMethod("error-could-not-get-method");
        }
        return new TestMethod(method.getName());
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TestMethod)) return false;
        TestMethod that = (TestMethod) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "TestMethod{" +
            "name='" + name + '\'' +
            '}';
    }
}
